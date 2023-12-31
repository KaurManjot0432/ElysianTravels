package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.destination.DestinationDTOMapper;
import com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTO;
import com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTOMapper;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.service.travelPackage.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTOMapper.mapToTravelPackageDTO;
import static com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTOMapper.mapTravelPackageDTOToTravelPackage;

/**
 * Controller class for managing travel packages.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/travelPackage")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    private final Logger logger = LoggerFactory.getLogger(TravelPackageController.class);

    @Autowired
    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTravelPackage(@RequestBody TravelPackageDTO travelPackageDTO) {
        try{
            TravelPackage travelPackage = mapTravelPackageDTOToTravelPackage(travelPackageDTO);
            List<Destination> destinationList = travelPackageDTO.getDestinationList()
                    .stream()
                    .map(DestinationDTOMapper::mapDestinationDTOTODestination)
                    .toList();

            return ResponseEntity.status(HttpStatus.CREATED).body(travelPackageService.createTravelPackage(travelPackage, destinationList));
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllTravelPackages() {
        try {
            List<TravelPackageDTO> travelPackageDTOS = travelPackageService.getAllTravelPackages()
                    .stream().map(TravelPackageDTOMapper::mapToTravelPackageDTO).toList();
            return ResponseEntity.ok(travelPackageDTOS);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/listDestinations/{travelPackageId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> printItinerary(@PathVariable Long travelPackageId) {
        try {
            TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
            TravelPackageDTO travelPackageDTO = mapToTravelPackageDTO(travelPackage);
            return ResponseEntity.ok(travelPackageDTO);
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
