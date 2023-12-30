package com.manjot.ElysianTravels.repository;

import com.manjot.ElysianTravels.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing destination in the database.
 * Extends JpaRepository for basic CRUD operations on Destination entities.
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query(value = "select d FROM Destination d WHERE LOWER(d.name)=LOWER(:name)")
    Destination findByName(String name);
}
