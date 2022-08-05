package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.TrackRecommendation;
import com.example.musicstorerecommendations.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/trackRecommendation")
public class TrackRecommendationController {
    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@RequestBody @Valid TrackRecommendation track){
        return service.createTrackRecommendation(track);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendation(){
        return service.getAllTrackRecommendation();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendationById(@PathVariable Integer id){
        return service.getTrackRecommendationById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation updateTrackRecommendation(@RequestBody @Valid TrackRecommendation track){
        return service.updateTrackRecommendation(track);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable Integer id){
        service.deleteTrackRecommendation(id);
    }
}

