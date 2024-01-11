package com.manjot.ElysianTravels.dto.activity;

import com.manjot.ElysianTravels.model.Activity;

public class ActivityDTOMapper {
    public static ActivityDTO mapActivityToActivityDTO(Activity activity) {
        return ActivityDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .capacity(activity.getCapacity() - activity.getPassengerList().size())
                .cost(activity.getCost())
                .build();
    }
    public static Activity mapActivityDTOToActivity(ActivityDTO activityDTO) {
        return Activity.builder()
                .capacity(activityDTO.getCapacity())
                .cost(activityDTO.getCost())
                .description(activityDTO.getDescription())
                .name(activityDTO.getName())
                .build();
    }

    public static ActivityDTO mapActivityToActivityPartialDTO(Activity activity) {
        return ActivityDTO.builder()
                .id(activity.getId())
                .name(activity.getName())
                .capacity(activity.getCapacity()-activity.getPassengerList().size())
                .build();
    }
}
