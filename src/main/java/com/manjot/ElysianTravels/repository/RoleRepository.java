package com.manjot.ElysianTravels.repository;

import com.manjot.ElysianTravels.model.ERole;
import com.manjot.ElysianTravels.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}