package com.manjot.ElysianTravels.service.destination;


import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.repository.DestinationRepository;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.manjot.ElysianTravels.dto.destination.DestinationDTOMapper.mapDestinationDTOTODestination;
@Service
public class DestinationServiceImpl implements DestinationService{

    private final DestinationRepository destinationRepository;

    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public DestinationServiceImpl(DestinationRepository destinationRepository,
                                  TravelPackageRepository travelPackageRepository) {
        this.destinationRepository = destinationRepository;
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public boolean addDestination(Long travelPackageId, Destination destination) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new EntityNotFoundException("Travel Package not found with ID: " + travelPackageId));

        List<Destination> destinations = travelPackage.getDestinationList();

        destination.setTravelPackage(travelPackage);
        destinations.add(destination);

        travelPackageRepository.save(travelPackage);
        return true;
    }
}
