package com.sherlock.farecalculationapplication.repository;

import com.sherlock.farecalculationapplication.model.Fare;
import com.sherlock.farecalculationapplication.model.Journey;
import com.sherlock.farecalculationapplication.model.Surcharge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Long> {

	@Query("SELECT j FROM Journey j where userId=:userId, journeyTime between :fromDate and :toDate")
	List<Journey> getJourneyBetweenDates(Long userId, LocalDateTime fromDate, LocalDateTime toDate);
}
