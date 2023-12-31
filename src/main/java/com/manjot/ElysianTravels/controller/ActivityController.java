package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.service.activity.ActivityService;
import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.dto.activity.ActivityDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controller for managing activities.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * Create a new activity for a destination.
     *
     * @param activityDTO The DTO containing details for creating a new activity.
     * @param destinationId The ID of the destination for which the activity is created.
     * @return ResponseEntity with the created activity or an error message.
     */
    @PostMapping("/{destinationId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createActivity(@RequestBody ActivityDTO activityDTO,
                                            @PathVariable Long destinationId) {
        try {
            Activity activity = ActivityDTOMapper.mapActivityDTOToActivity(activityDTO);
            boolean result = activityService.createActivity(activity, destinationId);

            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Activity created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create activity");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * Remove an activity from a destination.
     *
     * @param destinationId The ID of the destination from which the activity should be removed.
     * @param activityId The ID of the activity to be removed.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @DeleteMapping("/remove/{destinationId}/{activityId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeActivity(@PathVariable Long destinationId,
                                            @PathVariable Long activityId) {
        try {
            boolean result = activityService.removeActivity(destinationId, activityId);

            if (result) {
                return ResponseEntity.ok("Activity removed successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove activity");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }


    /**
     * Get a list of activities with available spaces.
     *
     * @return ResponseEntity with the list of activities with available spaces or an error message.
     */
    @GetMapping("/availableSpaces")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getActivitiesWithAvailableSpaces() {
        try {
            List<Activity> activities = activityService.getActivitiesWithAvailableSpaces();

            List<ActivityDTO> responseDTOs = activities.stream()
                    .map(ActivityDTOMapper::mapActivityToActivityDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * Register a passenger for an activity.
     *
     * @param passengerId The ID of the passenger to be registered.
     * @param activityId The ID of the activity for which the passenger is registering.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/register/{passengerId}/{activityId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> registerForActivity(@PathVariable Long passengerId,
                                                 @PathVariable Long activityId) {
        try {
            boolean result = activityService.registerForActivity(passengerId, activityId);

            if (result) {
                return ResponseEntity.ok("Passenger registered for activity successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register passenger for activity");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }


    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request " + e);
    }
}
