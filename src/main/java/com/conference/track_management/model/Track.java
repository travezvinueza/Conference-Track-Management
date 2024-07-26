package com.conference.track_management.model;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    private String trackName;
    private List<SessionItem> morningSession = new ArrayList<>();
    private String lunchTime = "12:00 PM";
    private List<SessionItem> afternoonSession = new ArrayList<>();
    private String networkingEventStart = "04:00 PM";
}
