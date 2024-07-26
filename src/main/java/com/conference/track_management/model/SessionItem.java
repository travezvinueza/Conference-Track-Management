package com.conference.track_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionItem {
    private String startTime;
    private String title;
    private int duration;
}
