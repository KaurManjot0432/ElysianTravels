package com.manjot.ElysianTravels.dto.travelPackage;

import com.manjot.ElysianTravels.dto.destination.DestinationDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TravelPackageDTO {
    private Long id;

    private String name;

    private int passengerCapacity;

    @Builder.Default
    List<DestinationDTO> destinationList = new ArrayList<>();
}