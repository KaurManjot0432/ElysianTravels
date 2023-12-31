package com.manjot.ElysianTravels.service.passenger;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.model.User;
import jakarta.validation.constraints.NotNull;

public interface PassengerService {
    public boolean subscribeToTravelPackage(Long travelPackageId, PassengerDTO passengerDTO);

    public PassengerListDTO getPassengerList(Long travelPackageId);

    public PassengerDTO getPassengerDetails(Long passengerId);
}
