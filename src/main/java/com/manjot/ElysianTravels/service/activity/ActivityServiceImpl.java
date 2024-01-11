package com.manjot.ElysianTravels.service.activity;

import com.manjot.ElysianTravels.exception.ElysianTravelsException;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.model.enums.PassengerType;
import com.manjot.ElysianTravels.repository.ActivityRepository;
import com.manjot.ElysianTravels.repository.DestinationRepository;
import com.manjot.ElysianTravels.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.manjot.ElysianTravels.model.enums.PassengerType.GOLD;
import static com.manjot.ElysianTravels.model.enums.PassengerType.PREMIUM;
import static com.manjot.ElysianTravels.utils.ErrorMessages.INSUFFICIENT_BALANCE;
import static com.manjot.ElysianTravels.utils.ValidationUtils.*;

/**
 * Service class for managing activities.
 */
@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    private final DestinationRepository destinationRepository;

    private final UserRepository userRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository,
                               DestinationRepository destinationRepository,
                               UserRepository userRepository
    ) {
        this.activityRepository = activityRepository;
        this.destinationRepository = destinationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a new activity for a destination.
     *
     * @param activity     The activity to be created.
     * @param destinationId The ID of the destination for which the activity is created.
     * @return True if the activity is created successfully, false otherwise.
     */
    @Override
    public boolean createActivity(Activity activity, Long destinationId){
        Destination destination = getDestinationById(destinationId);
        if (destination.getActivityList() == null) {
            destination.setActivityList(new ArrayList<>());
        }
        activity.setDestination(destination);
        destination.getActivityList().add(activity);

        destinationRepository.save(destination);

        return true;
    }
    /**
     * Remove an activity from a destination.
     *
     * @param destinationId The ID of the destination from which the activity should be removed.
     * @param activityId    The ID of the activity to be removed.
     * @return True if the activity is removed successfully, false otherwise.
     */
    @Override
    public boolean removeActivity(Long destinationId, Long activityId) {
        Destination destination = getDestinationById(destinationId);
        if (destination.getActivityList() != null) {
            Activity activity = getActivityById(activityId);

            destination.getActivityList().remove(activity);
            destinationRepository.save(destination);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Register a passenger for an activity.
     *
     * @param passengerId The ID of the passenger to be registered.
     * @param activityId  The ID of the activity for which the passenger is registering.
     * @return True if the passenger is registered successfully, false otherwise.
     */
    @Override
    @Transactional
    public boolean registerForActivity(Long passengerId, Long activityId) {
        User passenger = getUserById(passengerId);
        Activity activity = getActivityById(activityId);

        //perform validations
        validatePassengerSubscribedToActivityTravelPackage(activity, passenger);
        validateActivityCapacity(activity);
        validateExistingParticipation(activity, passenger);

        double costToPay = calculateCostToPay(passenger, activity);

        deductBalance(passenger, costToPay);

        participateInActivity(passenger, activity);

        return true;
    }

    /**
     * Return a list of activities with available spaces.
     *
     * @return The list of activities with available spaces.
     */
    public List<Activity> getActivitiesWithAvailableSpaces() {
        List<Activity> activities = activityRepository.findAll();

        return activities.stream()
                .filter(activity -> calculateAvailableSpaces(activity) > 0)
                .collect(Collectors.toList());
    }

    private int calculateAvailableSpaces(Activity activity) {
        int totalCapacity = activity.getCapacity();
        int occupiedSpaces = activity.getPassengerList().size();
        return totalCapacity - occupiedSpaces;
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with ID: " + userId));
    }

    private Activity getActivityById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with ID: " + activityId));
    }

    private Destination getDestinationById(Long destinationId) {
        return destinationRepository.findById(destinationId)
                .orElseThrow(() -> new EntityNotFoundException("Destination not found with ID: " + destinationId));
    }

    private double calculateCostToPay(User passenger, Activity activity) {
        double discountMultiplier = getDiscountMultiplier(passenger);
        return activity.getCost() * discountMultiplier;
    }

    private double getDiscountMultiplier(User passenger) {
        return switch (passenger.getPassengerType()) {
            case PREMIUM -> 0;
            case GOLD -> 0.9;
            default -> 1; // No discount for other passenger types
        };
    }

    private void deductBalance(User passenger, double costToPay) {
        if (costToPay > passenger.getBalance()) {
            throw new ElysianTravelsException(INSUFFICIENT_BALANCE);
        }
        passenger.setBalance(passenger.getBalance() - costToPay);
    }

    private void participateInActivity(User passenger, Activity activity) {
        activity.getPassengerList().add(passenger);
        passenger.getActivityList().add(activity);

        activityRepository.save(activity);
    }
}
