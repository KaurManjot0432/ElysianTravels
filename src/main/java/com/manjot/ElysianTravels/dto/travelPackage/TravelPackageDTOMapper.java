package com.manjot.ElysianTravels.dto.travelPackage;

import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.dto.activity.ActivityDTOMapper;
import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import com.manjot.ElysianTravels.model.TravelPackage;

import java.util.ArrayList;
import java.util.List;

public class TravelPackageDTOMapper {
    public static List<DestinationDTO> result;
    public static TravelPackageDTO mapToTravelPackageDTO(TravelPackage travelPackage) {
        result = new ArrayList<>();
        travelPackage.getDestinationList().forEach(destination -> {
            List<ActivityDTO> activityDTOList = destination.getActivityList()
                    .stream()
                    .map(ActivityDTOMapper::mapActivityToActivityDTO)
                    .toList();
            result.add(DestinationDTO.builder().id(destination.getId())
                    .activityList(activityDTOList)
                    .name(destination.getName())
                    .build());
        });
        return TravelPackageDTO.builder()
                .id(travelPackage.getId())
                .name(travelPackage.getName())
                .passengerCapacity(travelPackage.getPassengerCapacity())
                .destinationList(result)
                .build();
    }
    public static TravelPackage mapTravelPackageDTOToTravelPackage(TravelPackageDTO travelPackageDTO) {
        return TravelPackage.builder()
                .name(travelPackageDTO.getName())
                .passengerCapacity(travelPackageDTO.getPassengerCapacity())
                .build();
    }
}
