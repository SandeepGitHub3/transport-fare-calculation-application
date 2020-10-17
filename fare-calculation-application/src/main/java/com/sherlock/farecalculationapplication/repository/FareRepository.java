package com.sherlock.farecalculationapplication.repository;

import com.sherlock.farecalculationapplication.model.Fare;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FareRepository extends CrudRepository<Fare, Long> {

	@Query("SELECT f FROM Fare f where zoneFrom=:zoneFrom and zoneTo=:zoneTo")
	List<Fare> getFareForZones(String zoneFrom, String zoneTo);
}
