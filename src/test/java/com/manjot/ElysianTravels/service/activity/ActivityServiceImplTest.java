package com.manjot.ElysianTravels.service.activity;

import com.manjot.ElysianTravels.exception.ElysianTravelsException;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.model.Destination;
import com.manjot.ElysianTravels.model.User;
import com.manjot.ElysianTravels.model.enums.PassengerType;
import com.manjot.ElysianTravels.repository.ActivityRepository;
import com.manjot.ElysianTravels.repository.DestinationRepository;
import com.manjot.ElysianTravels.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createActivity() {
        Destination destination = new Destination();
        Activity activity = new Activity();
        Long destinationId = 1L;

        when(destinationRepository.findById(destinationId)).thenReturn(java.util.Optional.of(destination));

        boolean result = activityService.createActivity(activity, destinationId);

        assertTrue(result);
        assertEquals(destination, activity.getDestination());
        verify(destinationRepository, times(1)).save(destination);
    }

    @Test
    void removeActivity() {
        Destination destination = new Destination();
        Activity activity = new Activity();
        Long destinationId = 1L;
        Long activityId = 1L;

        when(destinationRepository.findById(destinationId)).thenReturn(java.util.Optional.of(destination));
        when(activityRepository.findById(activityId)).thenReturn(java.util.Optional.of(activity));

        boolean result = activityService.removeActivity(destinationId, activityId);

        assertFalse(result);
    }

}
