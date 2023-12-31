package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing passengers.
 */
@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    /**
     * Get the list of passengers for a specific travel package.
     *
     * @param travelPackageId The ID of the travel package for which passenger list is requested.
     * @return ResponseEntity with the passenger list or an error message.
     */
    @GetMapping("/list/{travelPackageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPassengerList(@PathVariable Long travelPackageId) {
        try {
            PassengerListDTO passengerListDTO = passengerService.getPassengerList(travelPackageId);
            return ResponseEntity.ok(passengerListDTO);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * Get details of a specific passenger.
     *
     * @param passengerId The ID of the passenger for which details are requested.
     * @return ResponseEntity with the passenger details or an error message.
     */
    @GetMapping("/{passengerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPassengerDetails(@PathVariable Long passengerId) {
        try {
            PassengerDTO passengerDTO = passengerService.getPassengerDetails(passengerId);
            return ResponseEntity.ok(passengerDTO);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    /**
     * Subscribe a passenger to a specific travel package.
     *
     * @param travelPackageId The ID of the travel package to which the passenger is subscribing.
     * @param passengerDTO    The DTO containing details for subscribing a passenger.
     * @return ResponseEntity indicating the success or failure of the operation.
     */
    @PostMapping("/subscribe/{travelPackageId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> subscribeToTravelPackage(
            @PathVariable Long travelPackageId,
            @RequestBody PassengerDTO passengerDTO) {
        try {
            boolean result = passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);
            if (result) {
                return ResponseEntity.ok("Passenger registered for Travel Package successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register passenger for Travel Package");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request " + e);
    }

}
