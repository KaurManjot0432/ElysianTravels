package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    @GetMapping("/list/{travelPackageId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getPassengerList(@PathVariable Long travelPackageId) {
        try {
            PassengerListDTO passengerListDTO = passengerService.getPassengerList(travelPackageId);
            return ResponseEntity.ok(passengerListDTO);
        } catch (Exception e) {
            return handleException(e);
        }
    }

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

    @PostMapping("/subscribe/{travelPackageId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> subscribeToTravelPackage(
            @PathVariable Long travelPackageId,
            @RequestBody PassengerDTO passengerDTO) {
        try {
            boolean result = passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request " + e);
    }

}
