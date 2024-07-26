package com.conference.track_management.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Talk {
    private String title;
    private int duration;
}
