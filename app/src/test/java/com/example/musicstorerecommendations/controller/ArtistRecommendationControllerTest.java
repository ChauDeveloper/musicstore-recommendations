package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.ArtistRecommendation;
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
@WebMvcTest(ArtistRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ArtistRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ServiceLayer service;
    ArtistRecommendation artistInput;
    ArtistRecommendation artistOutput;
    ArtistRecommendation artistUpdate;
    List<ArtistRecommendation> artistList;

    @Before
    public void setUp() throws Exception {
        artistInput = new ArtistRecommendation(2,5,false);
        artistOutput = new ArtistRecommendation(1,2,5,false);
        artistUpdate = new ArtistRecommendation(1,2,5,false);
        artistList = new ArrayList<>();
        artistList.add(artistOutput);

        when(this.service.createArtistRecommendation(artistInput)).thenReturn(artistOutput);
        when(this.service.getArtistRecommendationById(1)).thenReturn(artistOutput);
        when(this.service.getAllArtistRecommendation()).thenReturn(artistList);
        when(this.service.updateArtistRecommendation(artistUpdate)).thenReturn(artistUpdate);
    }

    @Test
    public void shouldReturnNewArtistRecommendationOnPostRequest() throws Exception{
        mockMvc.perform(post("/artistRecommendation")
                        .content(mapper.writeValueAsString(artistInput))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(artistOutput)));
    }

    @Test
    public void shouldReturnArtistRecommendationFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/artistRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnAllArtistRecommendationOnGetAllRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/artistRecommendation")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(artistList)));
    }

    @Test
    public void shouldReturnArtistRecommendationUponUpdateRequest() throws Exception{
        mockMvc.perform(put("/artistRecommendation")
                        .content(mapper.writeValueAsString(artistUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(artistUpdate)));
    }

    @Test
    public void shouldReturnNoContentStatusWhenDeleteArtistRecommendation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/artistRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldThrow422ExceptionWhenNotInputtingAnyValueForLiked () throws Exception {
        ArtistRecommendation artistError = new ArtistRecommendation();
        artistError.setArtistId(1);
        artistError.setUserId(1);

        mockMvc.perform(post("/artistRecommendation")
                        .content(mapper.writeValueAsString(artistError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenArtistIdIsEmpty () throws Exception {
        ArtistRecommendation artistError = new ArtistRecommendation();
        artistError.setUserId(1);
        artistError.setLiked(true);


        mockMvc.perform(post("/artistRecommendation")
                        .content(mapper.writeValueAsString(artistError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenUserIdIsEmpty () throws Exception {
        ArtistRecommendation artistError = new ArtistRecommendation();
        artistError.setArtistId(1);
        artistError.setLiked(true);

        mockMvc.perform(post("/artistRecommendation")
                        .content(mapper.writeValueAsString(artistError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}