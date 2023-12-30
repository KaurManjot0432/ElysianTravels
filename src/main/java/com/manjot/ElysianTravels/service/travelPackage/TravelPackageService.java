package com.manjot.ElysianTravels.service.travelPackage;

import com.manjot.ElysianTravels.model.TravelPackage;

/**
 * Service interface for managing travel packages.
 */
public interface TravelPackageService {

    public TravelPackage createTravelPackage(TravelPackage travelPackage);

    public TravelPackage getTravelPackageById(Long travelPackageId);
}
