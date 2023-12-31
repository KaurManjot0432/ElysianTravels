package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.service.passenger.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PassengerControllerTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private PassengerController passengerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPassengerList_Success() {
        Long travelPackageId = 1L;
        PassengerListDTO passengerListDTO = new PassengerListDTO(); // Create a PassengerListDTO object with appropriate data

        when(passengerService.getPassengerList(travelPackageId)).thenReturn(passengerListDTO);

        ResponseEntity<?> result = passengerController.getPassengerList(travelPackageId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(passengerListDTO, result.getBody());
        verify(passengerService, times(1)).getPassengerList(travelPackageId);
    }

    @Test
    void getPassengerDetails_Success() {
        Long passengerId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO(); // Create a PassengerDTO object with appropriate data

        when(passengerService.getPassengerDetails(passengerId)).thenReturn(passengerDTO);

        ResponseEntity<?> result = passengerController.getPassengerDetails(passengerId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(passengerDTO, result.getBody());
        verify(passengerService, times(1)).getPassengerDetails(passengerId);
    }

    @Test
    void subscribeToTravelPackage_Success() {
        Long travelPackageId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO(); // Create a PassengerDTO object with appropriate data

        when(passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO)).thenReturn(true);

        ResponseEntity<?> result = passengerController.subscribeToTravelPackage(travelPackageId, passengerDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Passenger registered for Travel Package successfully", result.getBody());
        verify(passengerService, times(1)).subscribeToTravelPackage(travelPackageId, passengerDTO);
    }

    @Test
    void subscribeToTravelPackage_Failure() {
        // Arrange
        Long travelPackageId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO(); // Create a PassengerDTO object with appropriate data

        when(passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO)).thenReturn(false);
        ResponseEntity<?> result = passengerController.subscribeToTravelPackage(travelPackageId, passengerDTO);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed to register passenger for Travel Package", result.getBody());
        verify(passengerService, times(1)).subscribeToTravelPackage(travelPackageId, passengerDTO);
    }
}
