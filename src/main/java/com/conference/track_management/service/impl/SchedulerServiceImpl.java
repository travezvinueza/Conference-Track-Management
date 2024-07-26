package com.conference.track_management.service.impl;

import com.conference.track_management.model.SessionItem;
import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;
import com.conference.track_management.service.SchedulerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private static final int MORNING_SESSION_START = 540; // 540 minutos equivalen a 9 am
    private static final int MORNING_SESSION_END = 720; // 720 minutos equivalen a 12 pm
    private static final int AFTERNOON_SESSION_START = 780; // 780 minutos equivalen a 13 pm
    private static final int NETWORKING_EVENT_START = 960; // 960 minutos equivalen a 16 pm.

    public List<Track> scheduleConference(List<Talk> talks) {
        List<Track> tracks = new ArrayList<>();
        List<Talk> remainingTalks = new ArrayList<>(talks);
        int trackNumber = 1;

        while (!remainingTalks.isEmpty()) {
            Track track = new Track();
            track.setTrackName("Track " + trackNumber);
            trackNumber++;

            remainingTalks = scheduleSession(track.getMorningSession(), remainingTalks, MORNING_SESSION_START, MORNING_SESSION_END);
            remainingTalks = scheduleSession(track.getAfternoonSession(), remainingTalks, AFTERNOON_SESSION_START, NETWORKING_EVENT_START);

            if (remainingTalks.isEmpty()) {
                SessionItem networkingEvent = new SessionItem();
                networkingEvent.setStartTime("04:00 PM");
                networkingEvent.setTitle("Networking Event");
                networkingEvent.setDuration(60);
                track.getAfternoonSession().add(networkingEvent);
                track.setNetworkingEventStart("04:00 PM");
            }

            tracks.add(track);
        }

        return tracks;
    }

    private List<Talk> scheduleSession(List<SessionItem> session, List<Talk> talks, int sessionStartTime, int sessionEndTime) {
        int currentTime = sessionStartTime;
        List<Talk> remainingTalks = new ArrayList<>();

        for (Talk talk : talks) {
            if (currentTime + talk.getDuration() <= sessionEndTime) {
                SessionItem sessionItem = new SessionItem();
                sessionItem.setStartTime(formatTime(currentTime));
                sessionItem.setTitle(talk.getTitle());
                sessionItem.setDuration(talk.getDuration());
                session.add(sessionItem);
                currentTime += talk.getDuration();
            } else {
                remainingTalks.add(talk);
            }
        }

        return remainingTalks;
    }

    private String formatTime(int timeInMinutes) {
        int hours = timeInMinutes / 60;
        int minutes = timeInMinutes % 60;
        String period = (hours < 12 || hours == 24) ? "AM" : "PM";
        if (hours > 12) hours -= 12;
        if (hours == 0) hours = 12;
        return String.format("%02d:%02d %s", hours, minutes, period);
    }
}
