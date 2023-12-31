package com.manjot.ElysianTravels.service.travelPackage;

import com.manjot.ElysianTravels.dto.destination.DestinationDTOMapper;
import com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTO;
import com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTOMapper;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTOMapper.mapTravelPackageDTOToTravelPackage;
import static com.manjot.ElysianTravels.utils.ErrorMessages.INVALID_TRAVEL_PACKAGE;

/**
 * Service class for managing travel packages.
 */
@Service
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    /**
     * Create a new travel package with the provided details.
     *
     * @param travelPackage    The travel package entity to be created.
     * @param destinationList  The list of destinations to be associated with the travel package.
     * @return The created travel package entity.
     */
    @Override
    public TravelPackage createTravelPackage(TravelPackage travelPackage, List<Destination> destinationList) {
        destinationList.forEach(destination -> destination.setTravelPackage(travelPackage));
        travelPackage.setDestinationList(destinationList);
        return travelPackageRepository.save(travelPackage);
    }

    /**
     * Get a travel package by its ID.
     *
     * @param travelPackageId The ID of the travel package to be retrieved.
     * @return The travel package entity.
     */
    @Override
    public TravelPackage getTravelPackageById(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_TRAVEL_PACKAGE));
    }

    /**
     * Get a list of all travel packages.
     *
     * @return The list of all travel packages.
     */
    @Override
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll().stream().toList();
    }
}
