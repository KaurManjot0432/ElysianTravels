package com.manjot.ElysianTravels.utils;

import com.manjot.ElysianTravels.exception.ElysianTravelsException;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.model.Destination;

import java.util.List;
import java.util.Objects;

import static com.manjot.ElysianTravels.utils.ErrorMessages.TRAVEL_PACKAGE_FULL;

/**
 * Utility class containing validation methods for various entities in the application.
 */
public class ValidationUtils {

    /**
     * Ensures that an activity has available space for passengers.
     *
     * @param activity The activity to be validated.
     * @throws ElysianTravelsException if the activity is full.
     */
    public static void validateActivityCapacity(Activity activity) {
        if (activity.getCapacity() <= activity.getPassengerList().size()) {
            throw new ElysianTravelsException(ErrorMessages.ACTIVITY_FULL);
        }
    }

    /**
     * Ensures that the passenger joining the activity has subscribed to the travel package that includes this activity
     * @param activity The activity passenger wants to join
     * @param passenger  passenger who is joining activity
     */
    public static void validatePassengerSubscribedToActivityTravelPackage(Activity activity, User passenger) {
        Destination activityDestination = activity.getDestination();
        TravelPackage destinationTravelPackage = activityDestination.getTravelPackage();

        // Check if the user has subscribed to the travel package of the destination
        if (passenger.getTravelPackage() == null || !passenger.getTravelPackage().equals(destinationTravelPackage)) {
            throw new IllegalArgumentException("User is not subscribed to the travel package of the destination.");
        }
    }

    /**
     * Checks if a destination name already exists in the list of destinations.
     *
     * @param destinations   The list of destinations to be checked.
     * @param destinationName The name of the destination to be validated.
     * @throws ElysianTravelsException if the destination name already exists.
     */
    public static void validateDestinationName(List<Destination> destinations, String destinationName) {
        if (destinations.stream().anyMatch(destination -> destination.getName().equals(destinationName))) {
            throw new ElysianTravelsException(ErrorMessages.DESTINATION_DUPLICATE);
        }
    }

    /**
     * Checks if a passenger has already participated in the given activity.
     *
     * @param activity  The activity to be checked.
     * @param passenger The passenger to be validated.
     * @throws ElysianTravelsException if the passenger has already participated.
     */
    public static void validateExistingParticipation(Activity activity, User passenger) {
        if (hasPassengerParticipatedInActivity(activity, passenger)) {
            throw new ElysianTravelsException(ErrorMessages.ALREADY_PARTICIPATED);
        }
    }

    /**
     * Checks if a travel package has available passenger capacity.
     *
     * @param travelPackage The travel package to be validated.
     * @throws ElysianTravelsException if the travel package is full.
     */
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
