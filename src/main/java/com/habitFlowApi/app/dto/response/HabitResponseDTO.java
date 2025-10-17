package com.habitFlowApi.app.dto.request;
import lombok.Data;

import java.time.LocalTime;

@Data
public class HabitResponseDTO {
    private long id;
    private String name;
    private String description;
    private boolean frequency; //0 - diary 1 - week
    private LocalTime suggestedTime;
    private boolean actve;
    private boolean complete;
    private long userId;
}
