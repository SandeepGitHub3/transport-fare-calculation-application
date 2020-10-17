package com.sherlock.farecalculationapplication.repository;

import com.sherlock.farecalculationapplication.model.Fare;
import com.sherlock.farecalculationapplication.model.Surcharge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SurchargeRepository extends CrudRepository<Surcharge, Long> {

	@Query("SELECT s FROM SurchargeRepository s where fare=:fare, applicableDay=:applicableDay and timeFrom<:journeyTime and timeTo>:journeyTime")
	List<Surcharge> getSurchargeFromFares(Fare fare, String applicableDay, LocalDateTime journeyTime);
}
