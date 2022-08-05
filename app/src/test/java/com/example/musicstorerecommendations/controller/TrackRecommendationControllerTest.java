package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.TrackRecommendation;
import com.example.musicstorerecommendations.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TrackRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ServiceLayer service;
    TrackRecommendation trackInput;
    TrackRecommendation trackOutput;
    TrackRecommendation trackUpdate;
    List<TrackRecommendation> trackList;

    @Before
    public void setUp() throws Exception {
        trackInput = new TrackRecommendation(2,5,false);
        trackOutput = new TrackRecommendation(1,2,5,false);
        trackUpdate = new TrackRecommendation(1,2,5,false);
        trackList = new ArrayList<>();
        trackList.add(trackOutput);

        when(this.service.createTrackRecommendation(trackInput)).thenReturn(trackOutput);
        when(this.service.getTrackRecommendationById(1)).thenReturn(trackOutput);
        when(this.service.getAllTrackRecommendation()).thenReturn(trackList);
        when(this.service.updateTrackRecommendation(trackUpdate)).thenReturn(trackUpdate);
    }

    @Test
    public void shouldReturnNewTrackRecommendationOnPostRequest() throws Exception{
        mockMvc.perform(post("/trackRecommendation")
                        .content(mapper.writeValueAsString(trackInput))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(trackOutput)));
    }

    @Test
    public void shouldReturnTrackRecommendationFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/trackRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnAllTrackRecommendationOnGetAllRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/trackRecommendation")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(trackList)));
    }

    @Test
    public void shouldReturnTrackRecommendationUponUpdateRequest() throws Exception{
        mockMvc.perform(put("/trackRecommendation")
                        .content(mapper.writeValueAsString(trackUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(trackUpdate)));
    }

    @Test
    public void shouldReturnNoContentStatusWhenDeleteTrackRecommendation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/trackRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldThrow422ExceptionWhenNotInputtingAnyValueForLiked () throws Exception {
        TrackRecommendation trackError = new TrackRecommendation();
        trackError.setTrackId(1);
        trackError.setUserId(1);

        mockMvc.perform(post("/trackRecommendation")
                        .content(mapper.writeValueAsString(trackError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenTrackIdIsEmpty () throws Exception {
        TrackRecommendation trackError = new TrackRecommendation();
        trackError.setUserId(1);
        trackError.setLiked(true);


        mockMvc.perform(post("/trackRecommendation")
                        .content(mapper.writeValueAsString(trackError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenUserIdIsEmpty () throws Exception {
        TrackRecommendation trackError = new TrackRecommendation();
        trackError.setTrackId(1);
        trackError.setLiked(true);

        mockMvc.perform(post("/trackRecommendation")
                        .content(mapper.writeValueAsString(trackError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}