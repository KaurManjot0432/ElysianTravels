package com.manjot.ElysianTravels.repository;

import com.manjot.ElysianTravels.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for managing activities.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}