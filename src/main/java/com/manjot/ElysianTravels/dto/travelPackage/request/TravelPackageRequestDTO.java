package com.manjot.ElysianTravels.dto.travelPackage.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) representing the request for creating a travel package.
 * This class is used to encapsulate data required when creating a new travel package.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TravelPackageRequestDTO {
    /**
     * The name of the travel package.
     */
    private String name;

    /**
     * The passenger capacity of the travel package.
     */
    private int passengerCapacity;
}
