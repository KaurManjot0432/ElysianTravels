package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.travelPackage.TravelPackageDTO;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.service.travelPackage.TravelPackageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TravelPackageControllerTest {

    @Mock
    private TravelPackageService travelPackageService;

    @InjectMocks
    private TravelPackageController travelPackageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTravelPackage_Success() {
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        TravelPackage travelPackage = new TravelPackage();
        List<Destination> destinationList = List.of(new Destination());

        when(travelPackageService.createTravelPackage(any(), any())).thenReturn(travelPackage);

        ResponseEntity<?> result = travelPackageController.createTravelPackage(travelPackageDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(travelPackageService, times(1)).createTravelPackage(any(), any());
    }

    @Test
    void getAllTravelPackages_Success() {
        // Arrange
        List<TravelPackage> travelPackageList = new ArrayList<>();
        when(travelPackageService.getAllTravelPackages()).thenReturn(travelPackageList);

        ResponseEntity<?> result = travelPackageController.getAllTravelPackages();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(travelPackageService, times(1)).getAllTravelPackages();
    }

    @Test
    void printItinerary_Success() {
        // Arrange
        Long travelPackageId = 1L;
        TravelPackage travelPackage = new TravelPackage();
        List<Destination> destinationList = new ArrayList<>();
        travelPackage.setDestinationList(destinationList);
        when(travelPackageService.getTravelPackageById(travelPackageId)).thenReturn(travelPackage);

        ResponseEntity<?> result = travelPackageController.printItinerary(travelPackageId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(travelPackageService, times(1)).getTravelPackageById(travelPackageId);
    }

}
