package com.manjot.ElysianTravels.dto.passenger;

import lombok.*;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PassengerListDTO {

    private String travelPackageName;

    private int passengerCapacity;

    private int passengerCount;

    private List<PassengerDTO> passengers;

}
