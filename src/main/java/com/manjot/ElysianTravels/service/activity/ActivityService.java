package com.manjot.ElysianTravels.service.activity;

import com.manjot.ElysianTravels.model.Activity;

import java.util.List;

public interface ActivityService {

    public boolean createActivity(Activity activity, Long destinationId);

    public boolean removeActivity(Long destinationId, Long activityId);

    public boolean registerForActivity(Long passengerId, Long activityId);

    public List<Activity> getActivitiesWithAvailableSpaces();
}
