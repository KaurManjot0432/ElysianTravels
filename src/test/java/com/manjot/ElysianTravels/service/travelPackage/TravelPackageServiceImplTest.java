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
        TravelPackage travelPackage = new TravelPackage();
        List<Destination> destinationList = new ArrayList<>();
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        destinationList.add(destination1);
        destinationList.add(destination2);

        when(travelPackageRepository.save(any(TravelPackage.class))).thenReturn(travelPackage);

        TravelPackage result = travelPackageService.createTravelPackage(travelPackage, destinationList);

        assertEquals(travelPackage, result);
        assertEquals(travelPackage, destination1.getTravelPackage());
        assertEquals(travelPackage, destination2.getTravelPackage());
        verify(travelPackageRepository, times(1)).save(travelPackage);
    }

    @Test
    public void testGetTravelPackageById() {

        Long travelPackageId = 1L;
        TravelPackage travelPackage = new TravelPackage();

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.of(travelPackage));

        TravelPackage result = travelPackageService.getTravelPackageById(travelPackageId);

        assertEquals(travelPackage, result);
    }

    @Test
    public void testGetTravelPackageByIdNotFound() {
        Long travelPackageId = 1L;

        when(travelPackageRepository.findById(travelPackageId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> travelPackageService.getTravelPackageById(travelPackageId));
    }

    @Test
    public void testGetAllTravelPackages() {
        List<TravelPackage> travelPackageList = new ArrayList<>();
        TravelPackage travelPackage1 = new TravelPackage();
        TravelPackage travelPackage2 = new TravelPackage();
        travelPackageList.add(travelPackage1);
        travelPackageList.add(travelPackage2);

        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);

        List<TravelPackage> result = travelPackageService.getAllTravelPackages();

        assertEquals(travelPackageList, result);
    }
}
