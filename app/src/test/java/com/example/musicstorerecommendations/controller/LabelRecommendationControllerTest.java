package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.LabelRecommendation;
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
@WebMvcTest(LabelRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LabelRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ServiceLayer service;
    LabelRecommendation labelInput;
    LabelRecommendation labelOutput;
    LabelRecommendation labelUpdate;
    List<LabelRecommendation> labelList;

    @Before
    public void setUp() throws Exception {
        labelInput = new LabelRecommendation(2,5,false);
        labelOutput = new LabelRecommendation(1,2,5,false);
        labelUpdate = new LabelRecommendation(1,2,5,false);
        labelList = new ArrayList<>();
        labelList.add(labelOutput);

        when(this.service.createLabelRecommendation(labelInput)).thenReturn(labelOutput);
        when(this.service.getLabelRecommendationById(1)).thenReturn(labelOutput);
        when(this.service.getAllLabelRecommendation()).thenReturn(labelList);
        when(this.service.updateLabelRecommendation(labelUpdate)).thenReturn(labelUpdate);
    }

    @Test
    public void shouldReturnNewLabelRecommendationOnPostRequest() throws Exception{
        mockMvc.perform(post("/labelRecommendation")
                        .content(mapper.writeValueAsString(labelInput))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(labelOutput)));
    }

    @Test
    public void shouldReturnLabelRecommendationFindById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/labelRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnAllLabelRecommendationOnGetAllRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/labelRecommendation")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(labelList)));
    }

    @Test
    public void shouldReturnLabelRecommendationUponUpdateRequest() throws Exception{
        mockMvc.perform(put("/labelRecommendation")
                        .content(mapper.writeValueAsString(labelUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(labelUpdate)));
    }

    @Test
    public void shouldReturnNoContentStatusWhenDeleteLabelRecommendation() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/labelRecommendation/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    public void shouldThrow422ExceptionWhenNotInputtingAnyValueForLiked () throws Exception {
        LabelRecommendation labelError = new LabelRecommendation();
        labelError.setLabelId(1);
        labelError.setUserId(1);

        mockMvc.perform(post("/labelRecommendation")
                        .content(mapper.writeValueAsString(labelError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenLabelIdIsEmpty () throws Exception {
        LabelRecommendation labelError = new LabelRecommendation();
        labelError.setUserId(1);
        labelError.setLiked(true);


        mockMvc.perform(post("/labelRecommendation")
                        .content(mapper.writeValueAsString(labelError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldThrow422ExceptionWhenUserIdIsEmpty () throws Exception {
        LabelRecommendation labelError = new LabelRecommendation();
        labelError.setLabelId(1);
        labelError.setLiked(true);

        mockMvc.perform(post("/labelRecommendation")
                        .content(mapper.writeValueAsString(labelError))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


}