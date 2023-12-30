package com.manjot.ElysianTravels.dto.travelPackage.request;

import com.manjot.ElysianTravels.model.TravelPackage;

public class TravelPackageRequestDTOMapper {
    /**
     * Maps a TravelPackageRequestDTO to a TravelPackage entity.
     *
     * @param travelPackageRequestDTO The DTO containing data for creating a travel package.
     * @return A mapped TravelPackage entity.
     */
    public static TravelPackage mapToTravelPackageRequestDTO(TravelPackageRequestDTO travelPackageRequestDTO) {
        return TravelPackage.builder()
                .name(travelPackageRequestDTO.getName())
                .passengerCapacity(travelPackageRequestDTO.getPassengerCapacity())
                .build();
    }
}
