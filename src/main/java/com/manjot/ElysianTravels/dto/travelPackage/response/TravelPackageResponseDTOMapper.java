package com.manjot.ElysianTravels.dto.travelPackage.response;

import com.manjot.ElysianTravels.model.TravelPackage;

public class TravelPackageResponseDTOMapper {
    /**
     * Maps a TravelPackage to a TravelPackageResponseDTO entity.
     *
     * @param TravelPackage
     * @return TravelPackageResponseDTO
     */
    public static TravelPackageResponseDTO mapToTravelPackageResponseDTO(TravelPackage travelPackage) {
        return TravelPackageResponseDTO.builder()
                .id(travelPackage.getId())
                .name(travelPackage.getName())
                .passengerCapacity(travelPackage.getPassengerCapacity())
                .build();
    }
}
