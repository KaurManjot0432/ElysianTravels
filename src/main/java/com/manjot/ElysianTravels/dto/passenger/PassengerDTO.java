package com.manjot.ElysianTravels.dto.passenger;

import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
import com.manjot.ElysianTravels.model.enums.PassengerType;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private Long id;

    private String passengerName;

    private Long passengerNumber;

    @NotBlank
    private PassengerType passengerType;

    @NotBlank
    double balance;

    @Builder.Default
    List<ActivityDTO> activities = new ArrayList<>();
}
