package com.manjot.ElysianTravels.service.passenger;

import com.manjot.ElysianTravels.dto.passenger.PassengerDTO;
import com.manjot.ElysianTravels.dto.passenger.PassengerListDTO;
import com.manjot.ElysianTravels.model.Activity;
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

import java.util.*;

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
    void subscribeToTravelPackage_Success() {
        Long travelPackageId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO();

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setPassengerCapacity(10);
        User passenger = new User();

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(java.util.Optional.of(travelPackage));
        when(userRepository.findById(passengerDTO.getId())).thenReturn(java.util.Optional.of(passenger));

        boolean result = passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);

        assertTrue(result);
        verify(userRepository, times(1)).save(passenger);
        verify(travelPackageRepository, times(1)).save(travelPackage);
    }

    @Test
    void subscribeToTravelPackage_InvalidPassenger() {

        Long travelPackageId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO();

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setPassengerCapacity(10);
        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));
        when(userRepository.findById(passengerDTO.getId())).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> {
            passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);
        });

        verify(userRepository, never()).save(any());
        verify(travelPackageRepository, never()).save(any());
    }

    @Test
    void subscribeToTravelPackage_InvalidTravelPackage() {
        Long travelPackageId = 1L;
        PassengerDTO passengerDTO = new PassengerDTO();

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            passengerService.subscribeToTravelPackage(travelPackageId, passengerDTO);
        });

        verify(userRepository, never()).save(any());
        verify(travelPackageRepository, never()).save(any());
    }

    @Test
    void getPassengerList_Success() {
        Long travelPackageId = 1L;

        TravelPackage travelPackage = new TravelPackage(); // Create a TravelPackage object with appropriate data
        travelPackage.setId(travelPackageId);
        List<User> passengers = new ArrayList<>(); // Add some passengers to the list
        travelPackage.setPassengerList(passengers);

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));

        PassengerListDTO result = passengerService.getPassengerList(travelPackageId);

        assertNotNull(result);
    }

    @Test
    void getPassengerDetails_Success() {

        Set<Activity> activityList = new HashSet<>();
        Long passengerId = 1L;

        User passenger = new User();
        passenger.setId(passengerId);
        passenger.setActivityList(activityList);
        when(userRepository.findById(passengerId)).thenReturn(Optional.of(passenger));

        PassengerDTO result = passengerService.getPassengerDetails(passengerId);

        assertNotNull(result);
        assertEquals(passengerId, result.getId());
    }
}
