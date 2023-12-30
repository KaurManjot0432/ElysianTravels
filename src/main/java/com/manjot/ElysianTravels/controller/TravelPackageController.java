package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.travelPackage.request.TravelPackageRequestDTO;
import com.manjot.ElysianTravels.dto.travelPackage.response.TravelPackageResponseDTO;
import com.manjot.ElysianTravels.dto.travelPackage.request.TravelPackageRequestDTOMapper;
import com.manjot.ElysianTravels.dto.travelPackage.response.TravelPackageResponseDTOMapper;
import com.manjot.ElysianTravels.service.travelPackage.TravelPackageService;
import com.manjot.ElysianTravels.model.TravelPackage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /**
     * Endpoint to create a new travel package.
     *
     * @param travelPackageRequestDTO The DTO containing details for creating a travel package.
     * @return ResponseEntity with the created travel package or an error message.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTravelPackage(@RequestBody TravelPackageRequestDTO travelPackageRequestDTO) {
        try {
            TravelPackage travelPackage = TravelPackageRequestDTOMapper.mapToTravelPackageRequestDTO(travelPackageRequestDTO);

            TravelPackage response = travelPackageService.createTravelPackage(travelPackage);

            TravelPackageResponseDTO responseDTO = TravelPackageResponseDTOMapper.mapToTravelPackageResponseDTO(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch(Exception e) {
            return handleException(e);
        }
    }

    /**
     * Endpoint to retrieve a travel package by its ID.
     *
     * @param travelPackageId The ID of the travel package.
     * @return ResponseEntity with the retrieved travel package or an error message.
     */
    @GetMapping("/{travelPackageId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTravelPackageById(@PathVariable Long travelPackageId) {
        try {
            TravelPackageResponseDTO responseDTO = TravelPackageResponseDTOMapper.mapToTravelPackageResponseDTO(
                    travelPackageService.getTravelPackageById(travelPackageId)
            );

            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Travel Package not found");
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<?> handleException(Exception e) {
        logger.error("Error occurred while processing request", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Some Error occurred while processing your request");
    }

}
