package com.manjot.ElysianTravels.service.passenger;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.exception.ElysianTravelsException;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import com.manjot.ElysianTravels.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.manjot.ElysianTravels.dto.passenger.PassengerDTOMapper.*;
import static com.manjot.ElysianTravels.utils.ErrorMessages.INVALID_PASSENGER;
import static com.manjot.ElysianTravels.utils.ErrorMessages.INVALID_TRAVEL_PACKAGE;
import static com.manjot.ElysianTravels.utils.ValidationUtils.validateTravelPackageCapacity;

@Service
public class PassengerServiceImpl implements PassengerService{

    private final UserRepository userRepository;

    private final TravelPackageRepository travelPackageRepository;

    @Autowired
    public PassengerServiceImpl(UserRepository userRepository,
                                TravelPackageRepository travelPackageRepository
    ) {
        this.userRepository = userRepository;
        this.travelPackageRepository = travelPackageRepository;
    }

    @Override
    public boolean subscribeToTravelPackage(Long travelPackageId, PassengerDTO passengerDTO) {
        TravelPackage travelPackage = getTravelPackageById(travelPackageId);

        validateTravelPackageCapacity(travelPackage);

        User passenger = mapPassengerInfoToPassenger(passengerDTO);
        passenger.setTravelPackage(travelPackage);
        userRepository.save(passenger);
        travelPackage.getPassengerList().add(passenger);

        travelPackageRepository.save(travelPackage);
        return true;
    }

    @Override
    public PassengerListDTO getPassengerList(Long travelPackageId) {
        TravelPackage travelPackage = getTravelPackageById(travelPackageId);
        return mapToPassengerListDTO(travelPackage);
    }

    @Override
    public PassengerDTO getPassengerDetails(Long passengerId) {
        User passenger = userRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_PASSENGER));
        return mapPassengerToPassengerInfo(passenger);
    }

    private TravelPackage getTravelPackageById(Long travelPackageId) {
        return travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_TRAVEL_PACKAGE));
    }
}
