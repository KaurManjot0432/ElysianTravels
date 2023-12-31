package com.manjot.ElysianTravels.service.destination;

import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import com.manjot.ElysianTravels.model.Destination;

/**
 * Service interface for managing destinations.
 */
public interface DestinationService {

    public boolean addDestination(Long travelPackageId, Destination destination);
}
