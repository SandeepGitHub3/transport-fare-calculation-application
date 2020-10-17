package com.sherlock.farecalculationapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Fare {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long fromZone;
	private Long toZone;
	private BigDecimal baseFare;
	private BigDecimal dailyCap;
	private BigDecimal weeklyCap;
}
