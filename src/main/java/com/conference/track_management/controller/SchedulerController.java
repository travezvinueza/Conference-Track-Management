package com.conference.track_management.controller;

import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;
import com.conference.track_management.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/my-scheduler")
@RequiredArgsConstructor
public class SchedulerController {
    private final SchedulerService schedulerService;

    @PostMapping("/create")
    public ResponseEntity<List<Track>> scheduleTalks(@RequestBody List<Talk> talks) {
        List<Track> tracks = schedulerService.scheduleConference(talks);
        return ResponseEntity.ok(tracks);
    }
}
