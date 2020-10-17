package com.sherlock.farecalculationapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Surcharge {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fare", referencedColumnName = "id")
	private Fare fare;

	private BigDecimal surcharge;
	/*CSV string containing Weekdays-Eg:'Monday,Tuesday'*/
	private String applicableDay;
	private LocalDateTime timeFrom;
	private LocalDateTime timeTo;
	private String surchargeType;

	/* Additionally we could also have columns to allow us to define surcharges for specific dates like holidays*/
}
