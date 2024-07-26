package com.conference.track_management.controller;

import com.conference.track_management.model.SessionItem;
import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;
import com.conference.track_management.service.SchedulerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SchedulerController.class)
class SchedulerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchedulerService schedulerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testScheduleTalks() throws Exception {
        List<Talk> talks = Arrays.asList(
                new Talk("Escribir pruebas rápidas contra Enterprise Rails", 60),
                new Talk("Exageran en Python", 45),
                new Talk("Lua for the Masses", 30),
                new Talk("Ruby Errors from Mismatched Gem Versions", 45),
                new Talk("Common Ruby Errors", 45),
                new Talk("Rails for Python Developers", 5),
                new Talk("Communicating Over Distance", 60),
                new Talk("Accounting-Driven Development", 45),
                new Talk("Woah", 30),
                new Talk("Sit Down and Write", 30),
                new Talk("Pair Programming vs Noise", 45),
                new Talk("Rails Magic", 60),
                new Talk("Ruby on Rails: Why We Should Move On", 60),
                new Talk("Clojure Ate Scala (on my project)", 45),
                new Talk("Programming in the Boondocks of Seattle", 30),
                new Talk("Ruby vs. Clojure for Back-End Development", 30),
                new Talk("Ruby on Rails Legacy App Maintenance", 60),
                new Talk("A World Without HackerNews", 30),
                new Talk("User Interface CSS in Rails Apps", 30)
        );

        List<Track> tracks = Arrays.asList(
                new Track("Track 1",
                        Arrays.asList(
                                new SessionItem("09:00 AM", "Escribir pruebas rápidas contra Enterprise Rails", 60),
                                new SessionItem("10:00 AM", "Exageran en Python", 45),
                                new SessionItem("10:45 AM", "Lua for the Masses", 30),
                                new SessionItem("11:15 AM", "Ruby Errors from Mismatched Gem Versions", 45)
                        ),
                        "12:00 PM",
                        Arrays.asList(
                                new SessionItem("01:00 PM", "Rails for Python Developers", 5),
                                new SessionItem("01:05 PM", "Communicating Over Distance", 60),
                                new SessionItem("02:05 PM", "Accounting-Driven Development", 45),
                                new SessionItem("02:50 PM", "Woah", 30),
                                new SessionItem("03:20 PM", "Sit Down and Write", 30),
                                new SessionItem("03:50 PM", "Pair Programming vs Noise", 45),
                                new SessionItem("04:35 PM", "Rails Magic", 60),
                                new SessionItem("05:35 PM", "Ruby on Rails: Why We Should Move On", 60),
                                new SessionItem("06:35 PM", "Clojure Ate Scala (on my project)", 45),
                                new SessionItem("07:20 PM", "Programming in the Boondocks of Seattle", 30),
                                new SessionItem("07:50 PM", "Ruby vs. Clojure for Back-End Development", 30),
                                new SessionItem("08:20 PM", "Ruby on Rails Legacy App Maintenance", 60),
                                new SessionItem("09:20 PM", "A World Without HackerNews", 30),
                                new SessionItem("09:50 PM", "User Interface CSS in Rails Apps", 30)
                        ),
                        "04:00 PM"
                ),
                new Track("Track 2",
                        Arrays.asList(
                                new SessionItem("09:00 AM", "Escribir pruebas rápidas contra Enterprise Rails", 60),
                                new SessionItem("10:00 AM", "Exageran en Python", 45),
                                new SessionItem("10:45 AM", "Lua for the Masses", 30),
                                new SessionItem("11:15 AM", "Ruby Errors from Mismatched Gem Versions", 45)
                        ),
                        "12:00 PM",
                        Arrays.asList(
                                new SessionItem("01:00 PM", "Rails for Python Developers", 5),
                                new SessionItem("01:05 PM", "Communicating Over Distance", 60),
                                new SessionItem("02:05 PM", "Accounting-Driven Development", 45),
                                new SessionItem("02:50 PM", "Woah", 30),
                                new SessionItem("03:20 PM", "Sit Down and Write", 30),
                                new SessionItem("03:50 PM", "Pair Programming vs Noise", 45),
                                new SessionItem("04:35 PM", "Rails Magic", 60),
                                new SessionItem("05:35 PM", "Ruby on Rails: Why We Should Move On", 60),
                                new SessionItem("06:35 PM", "Clojure Ate Scala (on my project)", 45),
                                new SessionItem("07:20 PM", "Programming in the Boondocks of Seattle", 30),
                                new SessionItem("07:50 PM", "Ruby vs. Clojure for Back-End Development", 30),
                                new SessionItem("08:20 PM", "Ruby on Rails Legacy App Maintenance", 60),
                                new SessionItem("09:20 PM", "A World Without HackerNews", 30),
                                new SessionItem("09:50 PM", "User Interface CSS in Rails Apps", 30)
                        ),
                        "04:00 PM"
                ),
                new Track("Track 3",
                        Arrays.asList(
                                new SessionItem("09:00 AM", "Escribir pruebas rápidas contra Enterprise Rails", 60),
                                new SessionItem("10:00 AM", "Exageran en Python", 45),
                                new SessionItem("10:45 AM", "Lua for the Masses", 30)
                        ),
                        "12:00 PM",
                        Arrays.asList(
                                new SessionItem("01:00 PM", "Rails for Python Developers", 5),
                                new SessionItem("01:05 PM", "Communicating Over Distance", 60),
                                new SessionItem("02:05 PM", "Accounting-Driven Development", 45),
                                new SessionItem("02:50 PM", "Woah", 30),
                                new SessionItem("03:20 PM", "Sit Down and Write", 30)
                        ),
                        "04:00 PM"
                )
        );

        when(schedulerService.scheduleConference(talks)).thenReturn(tracks);

        String jsonBody = objectMapper.writeValueAsString(talks);

        mockMvc.perform(post("/my-scheduler/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].trackName").value("Track 1"))
                .andExpect(jsonPath("$[0].networkingEventStart").value("04:00 PM"))
                .andExpect(jsonPath("$[0].morningSession[0].title").value("Escribir pruebas rápidas contra Enterprise Rails"))
                .andExpect(jsonPath("$[0].morningSession[0].duration").value(60))
                .andExpect(jsonPath("$[0].afternoonSession[1].title").value("Communicating Over Distance"))
                .andExpect(jsonPath("$[0].afternoonSession[1].duration").value(60))
                .andExpect(jsonPath("$[1].trackName").value("Track 2"))
                .andExpect(jsonPath("$[2].trackName").value("Track 3"));

        // Verificar detalles de la pista 1
        Track track1 = tracks.get(0);
        assertEquals(4, track1.getMorningSession().size());
        assertEquals(14, track1.getAfternoonSession().size());
        assertEquals("04:00 PM", track1.getNetworkingEventStart());
        assertEquals("12:00 PM", track1.getLunchTime());

        // Verificar detalles de la pista 2
        Track track2 = tracks.get(1);
        assertEquals(4, track2.getMorningSession().size());
        assertEquals(14, track2.getAfternoonSession().size());
        assertEquals("04:00 PM", track2.getNetworkingEventStart());
        assertEquals("12:00 PM", track2.getLunchTime());

        // Verificar detalles de la pista 3
        Track track3 = tracks.get(2);
        assertEquals(3, track3.getMorningSession().size());
        assertEquals(5, track3.getAfternoonSession().size());
        assertEquals("04:00 PM", track3.getNetworkingEventStart());
        assertEquals("12:00 PM", track3.getLunchTime());
    }

}