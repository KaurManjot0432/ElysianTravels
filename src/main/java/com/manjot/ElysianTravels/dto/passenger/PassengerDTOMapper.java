package com.manjot.ElysianTravels.dto.passenger;

import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.model.enums.PassengerType;

import java.util.List;

import static com.manjot.ElysianTravels.model.enums.PassengerType.GOLD;
import static com.manjot.ElysianTravels.model.enums.PassengerType.STANDARD;
import static com.manjot.ElysianTravels.model.enums.PassengerType.PREMIUM;

public class PassengerDTOMapper {
    public static PassengerListDTO mapToPassengerListDTO(TravelPackage travelPackage) {
        List<PassengerDTO> passengerInfos = travelPackage.getPassengerList().stream().map(passenger ->
                PassengerDTO.builder()
                        .id(passenger.getId())
                        .type(passenger.getPassengerType())
                        .passengerNumber(passenger.getId())
                        .name(passenger.getUsername())
                        .build()
        ).toList();
        return PassengerListDTO.builder()
                .travelPackageName(travelPackage.getName())
                .passengerCapacity(travelPackage.getPassengerCapacity())
                .passengerCount(travelPackage.getPassengerList().size())
                .passengers(passengerInfos).build();
    }

    public static PassengerDTO mapPassengerToPassengerInfo(User passenger) {
        List<ActivityDTO> activityDTOS = passenger.getActivityList().stream().map(activity -> ActivityDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .destination(activity.getDestination().getName())
                .cost(switch (passenger.getPassengerType()) {
                    case GOLD -> activity.getCost() * 0.9;
                    case PREMIUM -> 0;
                    case STANDARD -> activity.getCost();
                })
                .build()
        ).toList();
        return PassengerDTO.builder()
                .id(passenger.getId())
                .name(passenger.getUsername())
                .type(passenger.getPassengerType())
                .passengerNumber(passenger.getId())
                .balance(passenger.getPassengerType() != PassengerType.PREMIUM ? passenger.getBalance() : -1)
                .activities(activityDTOS)
                .build();
    }

    public static User mapPassengerInfoToPassenger(PassengerDTO passengerInfo) {
        return User.builder()
                .username(passengerInfo.getName())
                .email(passengerInfo.getEmail())
                .password(passengerInfo.getPassword())
                .passengerType(passengerInfo.getType())
                .id(passengerInfo.getPassengerNumber())
                .balance(passengerInfo.getBalance())
                .build();
    }
}
