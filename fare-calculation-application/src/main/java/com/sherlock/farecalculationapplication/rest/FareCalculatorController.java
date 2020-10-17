package com.sherlock.farecalculationapplication.rest;

import com.sherlock.farecalculationapplication.rest.dto.FareCalculationDto;
import com.sherlock.farecalculationapplication.service.FareCalculatorService;
import com.sherlock.farecalculationapplication.service.SurchargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FareCalculatorController {

	private final FareCalculatorService fareCalculatorService;
	private final SurchargeService surchargeService;

	@PostMapping(value = "/calculateFare")
	public BigDecimal calculateFare(List<FareCalculationDto> userJourneys) {
		return fareCalculatorService.calculate(userJourneys);
	}

	@GetMapping(value = "/populateSurcharge")
	public void populateSurcharge() {
		surchargeService.populateDefaultSurcharge();
	}
}
