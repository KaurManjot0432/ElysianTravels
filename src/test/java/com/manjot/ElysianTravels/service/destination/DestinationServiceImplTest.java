package com.manjot.ElysianTravels.service.destination;

import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.repository.DestinationRepository;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DestinationServiceImplTest {

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @InjectMocks
    private DestinationServiceImpl destinationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDestination() {
        Long travelPackageId = 1L;
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId(travelPackageId);

        Destination destination = new Destination();
        destination.setName("Test Destination");

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));
        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(travelPackage);

        boolean result = destinationService.addDestination(travelPackageId, destination);

        assertTrue(result);
        assertEquals(travelPackage, destination.getTravelPackage());
        verify(travelPackageRepository, times(1)).findById(travelPackageId);
        verify(travelPackageRepository, times(1)).save(travelPackage);
    }

    @Test
    void testAddDestinationTravelPackageNotFound() {

        Long travelPackageId = 1L;
        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.empty());

        Destination destination = new Destination();
        destination.setName("Test Destination");

        assertThrows(EntityNotFoundException.class,
                () -> destinationService.addDestination(travelPackageId, destination));
        verify(travelPackageRepository, times(1)).findById(travelPackageId);
        verify(travelPackageRepository, never()).save(any(TravelPackage.class));
    }
}
