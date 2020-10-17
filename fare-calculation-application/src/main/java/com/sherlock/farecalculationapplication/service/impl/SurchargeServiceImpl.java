package com.sherlock.farecalculationapplication.service.impl;

import com.sherlock.farecalculationapplication.model.Fare;
import com.sherlock.farecalculationapplication.model.Surcharge;
import com.sherlock.farecalculationapplication.repository.FareRepository;
import com.sherlock.farecalculationapplication.repository.SurchargeRepository;
import com.sherlock.farecalculationapplication.service.SurchargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*This Code is used just to populate sample Data.Can be easily replaced with proper crud operations for defining new Surcharge Rules*/
@Service
@RequiredArgsConstructor
public class SurchargeServiceImpl implements SurchargeService {

	private static final List<String> WEEK_DAYS = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
	private static final List<String> WEEK_END = Arrays.asList("Saturday", "Sunday");
	private final SurchargeRepository surchargeRepository;
	private final FareRepository fareRepository;

	@Override
	public void populateDefaultSurcharge() {
		populateDefaultSurcharge(1L);
		populateDefaultSurcharge(2L);
		populateDefaultSurcharge(3L);
		populateDefaultSurcharge(4L);
	}

	public void populateDefaultSurcharge(Long fareId) {

		Fare fare = fareRepository.findById(fareId).get();

		/*For Production we should ideally use Enums for Surcharge Type and Days*/
		/*WeekDays Morning*/
		WEEK_DAYS.forEach(
				day -> surchargeRepository.save(populateSurcharge(fare,
						new BigDecimal(5), "Peak Hour Weekday", day,
						LocalDateTime.of(2000, 01, 01, 07, 00),
						LocalDateTime.of(2099, 01, 01, 10, 30)))
		);

		/*WeekDays Evening*/
		WEEK_DAYS.forEach(
				day -> surchargeRepository.save(populateSurcharge(fare,
						new BigDecimal(5), "Peak Hour Weekday", day,
						LocalDateTime.of(2000, 01, 01, 17, 00),
						LocalDateTime.of(2099, 01, 01, 20, 00)))
		);

		/*Weekend Morning*/
		WEEK_END.forEach(
				day -> surchargeRepository.save(populateSurcharge(fare,
						new BigDecimal(5), "Peak Hour Weekend", day,
						LocalDateTime.of(2000, 01, 01, 9, 00),
						LocalDateTime.of(2099, 01, 01, 11, 00)))
		);

		/*Weekend Evening*/
		WEEK_END.forEach(
				day -> surchargeRepository.save(populateSurcharge(fare,
						new BigDecimal(5), "Peak Hour Weekend", day,
						LocalDateTime.of(2000, 01, 01, 18, 00),
						LocalDateTime.of(2099, 01, 01, 22, 00)))
		);

	}

	private Surcharge populateSurcharge(Fare fare,
			BigDecimal surcharge, String surchargeType, String applicableDay,
			LocalDateTime timeFrom,
			LocalDateTime timeTo) {
		return Surcharge.builder()
				.fare(fare)
				.surcharge(surcharge)
				.surchargeType(surchargeType)
				.applicableDay(applicableDay)
				.timeFrom(timeFrom)
				.timeTo(timeTo)
				.build();
	}
}
