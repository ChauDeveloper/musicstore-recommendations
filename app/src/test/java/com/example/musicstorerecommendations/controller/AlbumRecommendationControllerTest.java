package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.AlbumRecommendation;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
@WebMvcTest(AlbumRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AlbumRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ServiceLayer service;
    AlbumRecommendation albumInput;
    AlbumRecommendation albumOutput;
    AlbumRecommendation albumUpdate;
    List<AlbumRecommendation> albumList;

    @Before
    public void setUp() throws Exception {
        albumInput = new AlbumRecommendation(2,5,false);
        albumOutput = new AlbumRecommendation(1,2,5,false);
        albumUpdate = new AlbumRecommendation(1,2,5,false);
        albumList = new ArrayList<>();
        albumList.add(albumOutput);

        when(this.service.createAlbumRecommendation(albumInput)).thenReturn(albumOutput);
        when(this.service.getAlbumRecommendationById(1)).thenReturn(albumOutput);
        when(this.service.getAllAlbumRecommendation()).thenReturn(albumList);
        when(this.service.updateAlbumRecommendation(albumUpdate)).thenReturn(albumUpdate);
    }

    @Test
    public void shouldReturnNewAlbumRecommendationOnPostRequest() throws Exception{
        mockMvc.perform(post("/albumRecommendation")
                        .content(mapper.writeValueAsString(albumInput))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(albumOutput)));
    }

    @Test
    public void shouldReturnAlbumRecommendationFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/albumRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnAllAlbumRecommendationOnGetAllRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/albumRecommendation")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(albumList)));
    }

    @Test
    public void shouldReturnAlbumRecommendationUponUpdateRequest() throws Exception{
        mockMvc.perform(put("/albumRecommendation")
                        .content(mapper.writeValueAsString(albumUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(albumUpdate)));
    }

    @Test
    public void shouldReturnNoContentStatusWhenDeleteAlbumRecommendation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/albumRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldThrow422ExceptionWhenNotInputtingAnyValueForLiked () throws Exception {
        AlbumRecommendation albumError = new AlbumRecommendation();
        albumError.setAlbumId(1);
        albumError.setUserId(1);

        mockMvc.perform(post("/albumRecommendation")
                        .content(mapper.writeValueAsString(albumError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenAlbumIdIsEmpty () throws Exception {
        AlbumRecommendation albumError = new AlbumRecommendation();
        albumError.setUserId(1);
        albumError.setLiked(true);


        mockMvc.perform(post("/albumRecommendation")
                        .content(mapper.writeValueAsString(albumError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenUserIdIsEmpty () throws Exception {
        AlbumRecommendation albumError = new AlbumRecommendation();
        albumError.setAlbumId(1);
        albumError.setLiked(true);

        mockMvc.perform(post("/albumRecommendation")
                        .content(mapper.writeValueAsString(albumError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}