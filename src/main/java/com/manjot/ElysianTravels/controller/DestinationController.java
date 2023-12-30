package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.service.destination.DestinationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.manjot.ElysianTravels.dto.destination.DestinationDTOMapper.mapDestinationDTOTODestination;

/**
 * Controller class for managing destinations.
 */
@RestController
@RequestMapping("/api/destination")
public class DestinationController {

    private final DestinationService destinationService;

    private final Logger logger = LoggerFactory.getLogger(DestinationController.class);

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/{travelPackageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> addDestination(@PathVariable Long travelPackageId, @RequestBody DestinationDTO destinationDTO) {
        Destination destination = mapDestinationDTOTODestination(destinationDTO);
        return ResponseEntity.ok(destinationService.addDestination(travelPackageId, destination));
    }

    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request");
    }
}
