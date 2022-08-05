package com.example.musicstorerecommendations.service;

import com.example.musicstorerecommendations.model.AlbumRecommendation;
import com.example.musicstorerecommendations.model.ArtistRecommendation;
import com.example.musicstorerecommendations.model.LabelRecommendation;
import com.example.musicstorerecommendations.model.TrackRecommendation;
import com.example.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.example.musicstorerecommendations.repository.ArtistRecommendationRepository;
import com.example.musicstorerecommendations.repository.LabelRecommendationRepository;
import com.example.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ServiceLayer {
    AlbumRecommendationRepository albumRecommendationRepository;
    ArtistRecommendationRepository artistRecommendationRepository;
    LabelRecommendationRepository labelRecommendationRepository;
    TrackRecommendationRepository trackRecommendationRepository;

    @Autowired
    public ServiceLayer(AlbumRecommendationRepository albumRecommendationRepository, ArtistRecommendationRepository artistRecommendationRepository, LabelRecommendationRepository labelRecommendationRepository,TrackRecommendationRepository trackRecommendationRepository){
        this.albumRecommendationRepository = albumRecommendationRepository;
        this.artistRecommendationRepository = artistRecommendationRepository;
        this.labelRecommendationRepository = labelRecommendationRepository;
        this.trackRecommendationRepository = trackRecommendationRepository;
    }

    //LabelRecommendation service layer
    public LabelRecommendation createLabelRecommendation(LabelRecommendation labelRecommendation){
        if (labelRecommendation == null) throw new IllegalArgumentException("No labelRecommendation information filled in!");

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
        return labelRecommendation;
    }
    public List<LabelRecommendation> getAllLabelRecommendation(){
        List<LabelRecommendation> labelRecommendationList = labelRecommendationRepository.findAll();
        if (labelRecommendationList == null) {
            return null; }
        return labelRecommendationList;
    }
    public LabelRecommendation getLabelRecommendationById(Integer id){
        Optional<LabelRecommendation> labelRecommendation = labelRecommendationRepository.findById(id);
        if (labelRecommendation == null){
            return null;
        }
        return labelRecommendation.get();
    }
    public LabelRecommendation updateLabelRecommendation(LabelRecommendation labelRecommendation){
        if(labelRecommendation == null) throw new IllegalArgumentException("No labelRecommendation information provided!");
        if(labelRecommendation.getId()==null) throw new NoSuchElementException("Can't find a labelRecommendation by this ID");
        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
        return labelRecommendation;
    }
    public void deleteLabelRecommendation(Integer id){
        if (labelRecommendationRepository.findById(id) == null) throw new NoSuchElementException("No labelRecommendation found with this ID");
        labelRecommendationRepository.deleteById(id);
    }


    //ArtistRecommendation service layer
    public ArtistRecommendation createArtistRecommendation(ArtistRecommendation artistRecommendation){
        if (artistRecommendation == null) throw new IllegalArgumentException("No information was filled for this artistRecommendation");
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
        return artistRecommendation;
    }
    public List<ArtistRecommendation> getAllArtistRecommendation(){
        List<ArtistRecommendation> artistRecommendationList = artistRecommendationRepository.findAll();
        return artistRecommendationList;
    }
    public ArtistRecommendation getArtistRecommendationById(Integer id){
        Optional<ArtistRecommendation> artistRecommendation = artistRecommendationRepository.findById(id);
        if (artistRecommendationRepository.findById(id) == null) throw new NoSuchElementException("No ArtistRecommendation found at this ID");
        return artistRecommendation.get();
    }
    public ArtistRecommendation updateArtistRecommendation(ArtistRecommendation artistRecommendation){
        if (artistRecommendationRepository.findById(artistRecommendation.getId()) == null) throw new NoSuchElementException("No ArtistRecommendation found at this ID");
        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
        return artistRecommendation;
    }
    public void deleteArtistRecommendation(Integer id){
        if (artistRecommendationRepository.findById(id) == null) throw new NoSuchElementException("No ArtistRecommendation found at this ID");
        artistRecommendationRepository.deleteById(id);
    }


    //AlbumRecommendation service layer
    public AlbumRecommendation createAlbumRecommendation(AlbumRecommendation albumRecommendation){
        if (albumRecommendation == null) throw new NullPointerException("No information is filled for AlbumRecommendation");
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
        return albumRecommendation;
    }
    public List<AlbumRecommendation> getAllAlbumRecommendation(){
        return albumRecommendationRepository.findAll();
    }
    public AlbumRecommendation getAlbumRecommendationById(Integer id){
        Optional<AlbumRecommendation> albumRecommendation = albumRecommendationRepository.findById(id);
        if (albumRecommendation == null) throw new NoSuchElementException("No AlbumRecommendation found with this ID");
        return albumRecommendation.get();
    }
    public AlbumRecommendation updateAlbumRecommendation(AlbumRecommendation albumRecommendation){
        if (albumRecommendationRepository.findById(albumRecommendation.getId()) == null) throw new NoSuchElementException("Can't find this AlbumRecommendation at this ID");
        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
        return albumRecommendation;
    }
    public void deleteAlbumRecommendation(Integer id){
        if (albumRecommendationRepository.findById(id) == null) throw new NoSuchElementException("Can't find this AlbumRecommendation at this ID");
        albumRecommendationRepository.deleteById(id);
    }

    //TrackRecommendation service layer
    public TrackRecommendation createTrackRecommendation(TrackRecommendation trackRecommendation){
        if (trackRecommendation == null) throw new NullPointerException("No information is filled for trackRecommendation");
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
        return trackRecommendation;
    }
    public List<TrackRecommendation> getAllTrackRecommendation(){
        List<TrackRecommendation> trackRecommendationList = trackRecommendationRepository.findAll();
        return trackRecommendationList;
    }
    public TrackRecommendation getTrackRecommendationById(Integer id){
        Optional<TrackRecommendation> trackRecommendation = trackRecommendationRepository.findById(id);
        if (trackRecommendation == null) {return null;}
        return trackRecommendation.get();
    }
    public TrackRecommendation updateTrackRecommendation(TrackRecommendation trackRecommendation){
        if (trackRecommendationRepository.findById(trackRecommendation.getId()) == null ) throw new NoSuchElementException("can't find TrackRecommendation with this ID");
        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
        return trackRecommendation;
    }
    public void deleteTrackRecommendation(Integer id){
        if (trackRecommendationRepository.findById(id) == null ) throw new NoSuchElementException("can't find TrackRecommendation with this ID");
        trackRecommendationRepository.deleteById(id);
    }
}
