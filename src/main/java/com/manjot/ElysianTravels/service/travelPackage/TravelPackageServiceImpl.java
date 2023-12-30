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

@Service
public class TravelPackageServiceImpl implements TravelPackageService {
    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public TravelPackageServiceImpl(TravelPackageRepository travelPackageRepository) {
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public TravelPackage createTravelPackage(TravelPackage travelPackage, List<Destination> destinationList) {
        destinationList.forEach(destination -> destination.setTravelPackage(travelPackage));
        travelPackage.setDestinationList(destinationList);
        return travelPackageRepository.save(travelPackage);
    }
    @Override
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll().stream().toList();
    }
}
