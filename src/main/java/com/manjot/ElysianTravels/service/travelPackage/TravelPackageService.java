package com.manjot.ElysianTravels.service.travelPackage;

import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;

import java.util.List;

/**
 * Service interface for managing travel packages.
 */
public interface TravelPackageService {

    public TravelPackage createTravelPackage(TravelPackage travelPackage, List<Destination> destinationList);

    public TravelPackage getTravelPackageById(Long travelPackageId);

    public List<TravelPackage> getAllTravelPackages();
}
