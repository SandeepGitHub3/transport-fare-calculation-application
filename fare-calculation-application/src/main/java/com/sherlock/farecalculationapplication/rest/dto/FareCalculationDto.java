package com.sherlock.farecalculationapplication.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FareCalculationDto {

	private Long userId;
	private String day;
	private String zoneFrom;
	private String zoneTo;
	private String calculatedFare;
	private LocalDateTime journeyTime;
	private String remarks;
}
