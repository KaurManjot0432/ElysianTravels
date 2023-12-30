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

    String travelPackageName;

    int passengerCapacity;

    int passengerCount;

    List<PassengerDTO> passengers;

}
