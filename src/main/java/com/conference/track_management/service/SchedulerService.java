package com.conference.track_management.service;

import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;

import java.util.List;

public interface SchedulerService {
    List<Track> scheduleConference(List<Talk> talks);
}
