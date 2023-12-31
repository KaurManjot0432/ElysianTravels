package com.manjot.ElysianTravels.controller;

import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.service.activity.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ActivityControllerTest {

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createActivity_Success() {
        Long destinationId = 1L;
        ActivityDTO activityDTO = new ActivityDTO();
        Activity activity = new Activity();

        when(activityService.createActivity(any(), any())).thenReturn(true);

        ResponseEntity<?> result = activityController.createActivity(activityDTO, destinationId);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Activity created successfully", result.getBody());
    }

    @Test
    void createActivity_Failure() {
        Long destinationId = 1L;
        ActivityDTO activityDTO = new ActivityDTO();
        Activity activity = new Activity();

        when(activityService.createActivity(any(), any())).thenReturn(false);

        ResponseEntity<?> result = activityController.createActivity(activityDTO, destinationId);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed to create activity", result.getBody());
    }

    @Test
    void removeActivity_Success() {
        Long destinationId = 1L;
        Long activityId = 1L;

        when(activityService.removeActivity(any(), any())).thenReturn(true);

        ResponseEntity<?> result = activityController.removeActivity(destinationId, activityId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Activity removed successfully", result.getBody());
        verify(activityService, times(1)).removeActivity(destinationId, activityId);
    }

    @Test
    void removeActivity_Failure() {
        Long destinationId = 1L;
        Long activityId = 1L;

        when(activityService.removeActivity(any(), any())).thenReturn(false);

        ResponseEntity<?> result = activityController.removeActivity(destinationId, activityId);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed to remove activity", result.getBody());
        verify(activityService, times(1)).removeActivity(destinationId, activityId);
    }


    @Test
    void getActivitiesWithAvailableSpaces_Success() {
        when(activityService.getActivitiesWithAvailableSpaces()).thenReturn(Collections.singletonList(new Activity()));

        ResponseEntity<?> result = activityController.getActivitiesWithAvailableSpaces();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(activityService, times(1)).getActivitiesWithAvailableSpaces();
    }

    @Test
    void registerForActivity_Success() {
        Long passengerId = 1L;
        Long activityId = 1L;

        when(activityService.registerForActivity(any(), any())).thenReturn(true);

        ResponseEntity<?> result = activityController.registerForActivity(passengerId, activityId);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Passenger registered for activity successfully", result.getBody());
        verify(activityService, times(1)).registerForActivity(passengerId, activityId);
    }

    @Test
    void registerForActivity_Failure() {
        Long passengerId = 1L;
        Long activityId = 1L;

        when(activityService.registerForActivity(any(), any())).thenReturn(false);

        ResponseEntity<?> result = activityController.registerForActivity(passengerId, activityId);

        assertNotNull(result);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Failed to register passenger for activity", result.getBody());
        verify(activityService, times(1)).registerForActivity(passengerId, activityId);
    }

}

