package com.example.musicstorerecommendations.controller;

import com.example.musicstorerecommendations.model.AlbumRecommendation;
import com.example.musicstorerecommendations.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/albumRecommendation")
public class AlbumRecommendationController {
    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@RequestBody @Valid AlbumRecommendation album){
        return service.createAlbumRecommendation(album);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendation(){
        return service.getAllAlbumRecommendation();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendationById(@PathVariable Integer id){
        return service.getAlbumRecommendationById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation updateAlbumRecommendation(@RequestBody @Valid AlbumRecommendation album){
        return service.updateAlbumRecommendation(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable Integer id){
        service.deleteAlbumRecommendation(id);
    }
}
