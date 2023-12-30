package com.manjot.ElysianTravels.service.passenger;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;

public interface PassengerService {
    public boolean subscribeToTravelPackage(Long travelPackageId, PassengerDTO passengerDTO);

    public PassengerListDTO getPassengerList(Long travelPackageId);

    public PassengerDTO getPassengerDetails(Long passengerId);
}
