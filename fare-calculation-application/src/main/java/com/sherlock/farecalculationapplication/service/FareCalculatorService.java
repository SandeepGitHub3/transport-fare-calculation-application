package com.sherlock.farecalculationapplication.service;

import com.sherlock.farecalculationapplication.model.Journey;
import com.sherlock.farecalculationapplication.rest.dto.FareCalculationDto;

import java.math.BigDecimal;
import java.util.List;

public interface FareCalculatorService {

	BigDecimal calculate(List<FareCalculationDto> fareCalculationRequests);
}
