package com.conference.track_management.service;

import com.conference.track_management.model.SessionItem;
import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerService {
    private static final int MORNING_SESSION_END = 720; // 12:00 PM in minutes
    private static final int AFTERNOON_SESSION_START = 780; // 1:00 PM in minutes
    private static final int NETWORKING_EVENT_START = 960; // 4:00 PM in minutes
    private static final int NETWORKING_EVENT_END = 1020; // 5:00 PM en minutos

    public List<Track> scheduleConference(List<Talk> talks) {
        List<Track> tracks = new ArrayList<>();
        List<Talk> remainingTalks = new ArrayList<>(talks);
        int trackNumber = 1; // Número de pista

        // Bucle para crear pistas mientras queden charlas por programar
        while (!remainingTalks.isEmpty()) {
            Track track = new Track();
            track.setTrackName("Track " + trackNumber);
            trackNumber++; // Incrementar el número de pista

            // Programar las charlas de la sesión de la mañana
            remainingTalks = scheduleSession(track.getMorningSession(), remainingTalks, 540, MORNING_SESSION_END);  // Sesión de la mañana desde las 9:00 AM (540 minutos desde la medianoche)
            // Programar las charlas de la sesión de la tarde
            remainingTalks = scheduleSession(track.getAfternoonSession(), remainingTalks, AFTERNOON_SESSION_START, NETWORKING_EVENT_START); // Sesión de la tarde

            // Añadir evento de networking si ya no quedan charlas por programar
            if (remainingTalks.isEmpty()) {
                SessionItem networkingEvent = new SessionItem();
                networkingEvent.setStartTime("04:00 PM");
                networkingEvent.setTitle("Networking Event");
                networkingEvent.setDuration(60); /// El evento de networking dura 1 hora
                track.getAfternoonSession().add(networkingEvent);
                track.setNetworkingEventStart("04:00 PM");
            }

            // Añade el track actual a la lista de tracks
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
        if (hours == 0) hours = 12; // 12 AM or 12 PM
        return String.format("%02d:%02d %s", hours, minutes, period);
    }
}
