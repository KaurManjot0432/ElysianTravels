package com.manjot.ElysianTravels.dto.passenger;

import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.model.enums.PassengerType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PassengerDTO {
    private Long id;

    String name;
    String email;
    String password;

    Long passengerNumber;

    PassengerType type;

    double balance;

    @Builder.Default
    List<ActivityDTO> activities = new ArrayList<>();
}
