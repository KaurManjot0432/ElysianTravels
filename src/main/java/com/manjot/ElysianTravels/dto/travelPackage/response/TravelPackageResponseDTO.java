package com.manjot.ElysianTravels.dto.travelPackage.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) representing the response for a travel package.
 * This class is used to encapsulate data when retrieving information about a travel package.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TravelPackageResponseDTO {
    /**
     * The unique identifier of the travel package.
     */
    private Long id;

    /**
     * The name of the travel package.
     */
    private String name;

    /**
     * The passenger capacity of the travel package.
     */
    private int passengerCapacity;
}
