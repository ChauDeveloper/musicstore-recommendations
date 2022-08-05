package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.LabelRecommendation;
import com.example.musicstorerecommendations.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/labelRecommendation")
public class LabelRecommendationController {
    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@RequestBody @Valid LabelRecommendation label){
        return service.createLabelRecommendation(label);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendation(){
        return service.getAllLabelRecommendation();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendationById(@PathVariable Integer id){
        return service.getLabelRecommendationById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation updateLabelRecommendation(@RequestBody @Valid LabelRecommendation label){
        return service.updateLabelRecommendation(label);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable Integer id){
        service.deleteLabelRecommendation(id);
    }
}
