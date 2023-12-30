package com.manjot.ElysianTravels.dto.destination;

import com.manjot.ElysianTravels.dto.activity.ActivityDTOMapper;
import com.manjot.ElysianTravels.model.Activity;
import com.manjot.ElysianTravels.model.Destination;

import java.util.List;

public class DestinationDTOMapper {
    public static Destination mapDestinationDTOTODestination(DestinationDTO destinationDTO) {
        Destination destination = Destination.builder()
                .name(destinationDTO.getName())
                .build();
        List<Activity> activityList = destinationDTO.getActivityList().stream().map(ActivityDTOMapper::mapActivityDTOToActivity).toList();
        activityList.forEach(activity -> activity.setDestination(destination));
        destination.setActivityList(activityList);
        return destination;
    }
}
