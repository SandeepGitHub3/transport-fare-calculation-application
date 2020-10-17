package com.sherlock.farecalculationapplication.service.impl;

import com.sherlock.farecalculationapplication.model.Fare;
import com.sherlock.farecalculationapplication.model.Journey;
import com.sherlock.farecalculationapplication.model.Surcharge;
import com.sherlock.farecalculationapplication.repository.FareRepository;
import com.sherlock.farecalculationapplication.repository.JourneyRepository;
import com.sherlock.farecalculationapplication.repository.SurchargeRepository;
import com.sherlock.farecalculationapplication.rest.dto.FareCalculationDto;
import com.sherlock.farecalculationapplication.service.FareCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FareCalculatorServiceImpl implements FareCalculatorService {

	private final FareRepository fareRepository;
	private final SurchargeRepository surchargeRepository;
	private final JourneyRepository journeyRepository;

	@Override
	public BigDecimal calculate(List<FareCalculationDto> fareCalculationRequests) {
		return fareCalculationRequests.stream()
				.map(this::getUserFareDetails)
				.reduce(BigDecimal::add).get();
	}

	private BigDecimal getUserFareDetails(FareCalculationDto fareCalculationRequest){

		//get Fare Details from Fare table
		//get Surcharge
		//calculate total
		//check Daily Weekly Cap
		//return final
		/* We can check if it returns multiple results and throw an exception in case multiple records found as there can be only one
		* Fare record defined for a given combination of Form-To Zones*/
		Fare baseFare = fareRepository.getFareForZones(fareCalculationRequest.getZoneFrom(),fareCalculationRequest.getZoneTo()).get(0);

		/*Time Adjusted to Have a Constant Year/Month/Date as we are only interested in Time and not date.
		There could be better ways to check and store just time in DB*/
		LocalDateTime journeyTime = fareCalculationRequest.getJourneyTime().withYear(2000).withMonth(01).withDayOfMonth(01);

		/*Again we could extend this part to assign some sort of weight and pick the surcharge with highest weight.Will depende on the business scenario*/
		Surcharge applicableSurcharge = surchargeRepository.getSurchargeFromFares(baseFare,fareCalculationRequest.getDay(),journeyTime).get(0);

		BigDecimal totalFare = baseFare.getBaseFare().add(applicableSurcharge.getSurcharge());

		List<Journey> currentDayJourneys = journeyRepository.getJourneyBetweenDates(fareCalculationRequest.getUserId(),
				fareCalculationRequest.getJourneyTime().toLocalDate().atStartOfDay(),
				fareCalculationRequest.getJourneyTime().plusDays(1).toLocalDate().atStartOfDay());

		List<Journey> currentWeekJourneys = journeyRepository.getJourneyBetweenDates(fareCalculationRequest.getUserId(),
				fareCalculationRequest.getJourneyTime().toLocalDate().with(DayOfWeek.MONDAY).atStartOfDay(),
				fareCalculationRequest.getJourneyTime().toLocalDate().with(DayOfWeek.SUNDAY).plusDays(1).atStartOfDay());

		BigDecimal currentWeekTotalFare = currentWeekJourneys.stream().map(Journey::getApplicableFare).reduce(BigDecimal::add).get();
		BigDecimal currentDayTotalFare = currentDayJourneys.stream().map(Journey::getApplicableFare).reduce(BigDecimal::add).get();

		/*Check Conditions*/
		if(currentDayTotalFare.compareTo(baseFare.getDailyCap())==0)
			totalFare=baseFare.getDailyCap();


		return totalFare;
	}
}
