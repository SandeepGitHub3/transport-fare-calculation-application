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
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Journey {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fare", referencedColumnName = "id")
	private Fare fareId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "surcharge", referencedColumnName = "id")
	private Surcharge surchargeId;

	private BigDecimal applicableFare;

	private LocalDateTime journeyTime;
}
