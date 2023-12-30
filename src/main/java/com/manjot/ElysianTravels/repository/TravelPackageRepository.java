package com.manjot.ElysianTravels.repository;

import com.manjot.ElysianTravels.model.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing travel packages in the database.
 * Extends JpaRepository for basic CRUD operations on TravelPackage entities.
 */
@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
}
