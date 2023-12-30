package com.manjot.ElysianTravels.service.travelPackage;

import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.TravelPackage;
import com.manjot.ElysianTravels.repository.TravelPackageRepository;
import com.manjot.ElysianTravels.service.travelPackage.TravelPackageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TravelPackageServiceImplTest {

    @Mock
    private TravelPackageRepository travelPackageRepository;

    @InjectMocks
    private TravelPackageServiceImpl travelPackageService;

    @Before
    public void setUp() {
        // Additional setup if needed
    }

    @Test
    public void testCreateTravelPackage() {
        // Mock data
        TravelPackage travelPackage = new TravelPackage();
        List<Destination> destinationList = new ArrayList<>();
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        destinationList.add(destination1);
        destinationList.add(destination2);

        // Mock repository behavior
        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(travelPackage);

        // Test the service method
        TravelPackage result = travelPackageService.createTravelPackage(travelPackage, destinationList);

        // Assertions
        assertEquals(travelPackage, result);
        assertEquals(travelPackage, destination1.getTravelPackage());
        assertEquals(travelPackage, destination2.getTravelPackage());
        verify(travelPackageRepository, times(1)).save(travelPackage);
    }

    @Test
    public void testGetTravelPackageById() {
        // Mock data
        Long travelPackageId = 1L;
        TravelPackage travelPackage = new TravelPackage();

        // Mock repository behavior
        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));

        // Test the service method
        TravelPackage result = travelPackageService.getTravelPackageById(travelPackageId);

        // Assertions
        assertEquals(travelPackage, result);
    }

    @Test
    public void testGetTravelPackageByIdNotFound() {
        // Mock data
        Long travelPackageId = 1L;

        // Mock repository behavior
        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.empty());

        // Test the service method and expect an exception
        assertThrows(EntityNotFoundException.class, () -> travelPackageService.getTravelPackageById(travelPackageId));
    }

    @Test
    public void testGetAllTravelPackages() {
        // Mock data
        List<TravelPackage> travelPackageList = new ArrayList<>();
        TravelPackage travelPackage1 = new TravelPackage();
        TravelPackage travelPackage2 = new TravelPackage();
        travelPackageList.add(travelPackage1);
        travelPackageList.add(travelPackage2);

        // Mock repository behavior
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        // Test the service method
        List<TravelPackage> result = travelPackageService.getAllTravelPackages();

        // Assertions
        assertEquals(travelPackageList, result);
    }

    // Add more test cases if needed

}
