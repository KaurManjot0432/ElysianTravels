package com.manjot.ElysianTravels.service.travelPackage;

import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    /**
     * Creates a new travel package.
     *
     * @param travelPackage The travel package entity to be created.
     * @return The created travel package.
     */
    @Override
    public TravelPackage createTravelPackage(TravelPackage travelPackage) {
        return travelPackageRepository.save(travelPackage);
    }

    /**
     * Retrieves a travel package by its unique identifier.
     *
     * @param travelPackageId The unique identifier of the travel package.
     * @return The retrieved travel package.
     */
    @Override
    public TravelPackage getTravelPackageById(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new EntityNotFoundException("Travel package not found with ID: " + travelPackageId));
    }
}
