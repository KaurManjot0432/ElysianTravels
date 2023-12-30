package com.manjot.ElysianTravels.utils;

import com.manjot.ElysianTravels.exception.ElysianTravelsException;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.model.Destination;

import java.util.List;
import java.util.Objects;

import static com.manjot.ElysianTravels.utils.ErrorMessages.TRAVEL_PACKAGE_FULL;

public class ValidationUtils {

    // Ensure activity has available space for passengers
    public static void validateActivityCapacity(Activity activity) {
        if (activity.getCapacity() <= activity.getPassengerList().size()) {
            throw new ElysianTravelsException(ErrorMessages.ACTIVITY_FULL);
        }
    }

    // Check if destination name already exists in the list of destinations
    public static void validateDestinationName(List<Destination> destinations, String destinationName) {
        if (destinations.stream().anyMatch(destination -> destination.getName().equals(destinationName))) {
            throw new ElysianTravelsException(ErrorMessages.DESTINATION_DUPLICATE);
        }
    }

    // Check if the passenger has already participated in the given activity
    public static void validateExistingParticipation(Activity activity, User passenger) {
        if (hasPassengerParticipatedInActivity(activity, passenger)) {
            throw new ElysianTravelsException(ErrorMessages.ALREADY_PARTICIPATED);
        }
    }

    public static void validateTravelPackageCapacity(TravelPackage travelPackage) {
        if (travelPackage.getPassengerCapacity()-travelPackage.getPassengerList().size()<=0) {
            throw new ElysianTravelsException(TRAVEL_PACKAGE_FULL);
        }
    }

    private static boolean hasPassengerParticipatedInActivity(Activity activity, User passenger) {
        return passenger.getActivityList()
                .stream().
                anyMatch(activity_ -> Objects.equals(activity_.getName(), activity.getName()));
    }
}
