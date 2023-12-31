package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.service.destination.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DestinationControllerTest {

    @Mock
    private DestinationService destinationService;

    @InjectMocks
    private DestinationController destinationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addDestination_Success() {
        Long travelPackageId = 1L;
        DestinationDTO destinationDTO = new DestinationDTO();
        Destination destination = new Destination();

        when(destinationService.addDestination(any(), any())).thenReturn(true);

        ResponseEntity<?> result = destinationController.addDestination(travelPackageId, destinationDTO);

        assertNotNull(result);

    }
}