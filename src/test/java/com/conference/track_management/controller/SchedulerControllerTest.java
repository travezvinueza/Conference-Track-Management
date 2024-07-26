package com.conference.track_management.controller;

import com.conference.track_management.model.Talk;
import com.conference.track_management.model.Track;
import com.conference.track_management.service.impl.SchedulerServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class SchedulerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SchedulerServiceImpl schedulerServiceImpl;

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

        String jsonBody = objectMapper.writeValueAsString(talks);

        ResultActions resultActions = mockMvc.perform(post("/my-scheduler/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

        String responseContent = resultActions.andReturn().getResponse().getContentAsString();
        List<Track> tracks = objectMapper.readValue(responseContent, new TypeReference<List<Track>>() {});

        // Verificar detalles de la pista 1
        Track track1 = tracks.get(0);
        assertEquals(4, track1.getMorningSession().size());
        assertEquals(4, track1.getAfternoonSession().size());
        assertEquals("04:00 PM", track1.getNetworkingEventStart());
        assertEquals("12:00 PM", track1.getLunchTime());

        // Verificar detalles de la pista 2
        Track track2 = tracks.get(1);
        assertEquals(4, track2.getMorningSession().size());
        assertEquals(4, track2.getAfternoonSession().size());
        assertEquals("04:00 PM", track2.getNetworkingEventStart());
        assertEquals("12:00 PM", track2.getLunchTime());

        // Verificar detalles de la pista 3
        Track track3 = tracks.get(2);
        assertEquals(3, track3.getMorningSession().size());
        assertEquals(1, track3.getAfternoonSession().size());
        assertEquals("04:00 PM", track3.getNetworkingEventStart());
        assertEquals("12:00 PM", track3.getLunchTime());
    }

}