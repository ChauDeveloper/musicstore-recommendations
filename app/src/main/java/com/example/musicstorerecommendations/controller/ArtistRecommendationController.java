package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.ArtistRecommendation;
import com.example.musicstorerecommendations.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/artistRecommendation")
public class ArtistRecommendationController {
    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRecommendation(@RequestBody @Valid ArtistRecommendation artist){
        return service.createArtistRecommendation(artist);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendation(){
        return service.getAllArtistRecommendation();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRecommendationById(@PathVariable Integer id){
        return service.getArtistRecommendationById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation updateArtistRecommendation(@RequestBody @Valid ArtistRecommendation artist){
        return service.updateArtistRecommendation(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable Integer id){
        service.deleteArtistRecommendation(id);
    }
}
