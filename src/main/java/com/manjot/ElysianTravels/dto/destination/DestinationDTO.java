package com.manjot.ElysianTravels.dto.destination;
import com.manjot.ElysianTravels.dto.activity.ActivityDTO;
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
public class DestinationDTO {

    private Long id;

    private String name;

    @Builder.Default
    private List<ActivityDTO> activityList = new ArrayList<>();

}