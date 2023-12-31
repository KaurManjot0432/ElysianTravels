package com.manjot.ElysianTravels.service.passenger;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import com.manjot.ElysianTravels.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassengerServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubscribeToTravelPackage() {
        // Arrange
//        long travelPackageId = 1L;
//        PassengerDTO passengerDTO = new PassengerDTO(/* populate with necessary data */);
//
//        TravelPackage travelPackage = new TravelPackage();
//        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));
//        when(userRepository.save(any(User.class))).thenReturn(new User()); // Adjust if needed
//        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(new TravelPackage()); // Adjust if needed
//
//        // Act
//        boolean result = passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);
//
//        // Assert
//        assertTrue(result);
//        assertEquals(1, travelPackage.getPassengerList().size());
//        verify(userRepository, times(1)).save(any(User.class));
//        verify(travelPackageRepository, times(1)).save(any(TravelPackage.class));
    }
    @Test
    void testGetPassengerList() {
        // Arrange
//        long travelPackageId = 1L;
//
//        TravelPackage travelPackage = new TravelPackage();
//        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));
//
//        // Act
//        PassengerListDTO passengerListDTO = passengerService.getPassengerList(travelPackageId);
//
//        // Assert
//        assertNotNull(passengerListDTO);
//        // Add assertions for the content of passengerListDTO if needed
    }

    @Test
    void testGetPassengerDetails() {
        // Arrange
//        long passengerId = 1L;
//
//        User passenger = new User();
//        when(userRepository.findById(passengerId)).thenReturn(Optional.of(passenger));
//
//        // Act
//        PassengerDTO passengerDTO = passengerService.getPassengerDetails(passengerId);
//
//        // Assert
//        assertNotNull(passengerDTO);
        // Add assertions for the content of passengerDTO if needed
    }
}
