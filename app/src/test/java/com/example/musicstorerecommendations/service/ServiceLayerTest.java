package com.example.musicstorerecommendations.service;

import com.example.musicstorerecommendations.model.AlbumRecommendation;
import com.example.musicstorerecommendations.model.ArtistRecommendation;
import com.example.musicstorerecommendations.model.LabelRecommendation;
import com.example.musicstorerecommendations.model.TrackRecommendation;
import com.example.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.example.musicstorerecommendations.repository.ArtistRecommendationRepository;
import com.example.musicstorerecommendations.repository.LabelRecommendationRepository;
import com.example.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


@RunWith(SpringRunner.class)
public class ServiceLayerTest {
    ServiceLayer service;
    AlbumRecommendationRepository albumRepository;
    ArtistRecommendationRepository artistRepository;
    LabelRecommendationRepository labelRepository;
    TrackRecommendationRepository trackRepository;
    AlbumRecommendation albumReturn;
    ArtistRecommendation artistReturn;
    LabelRecommendation labelReturn;
    TrackRecommendation trackReturn;
    AlbumRecommendation albumInput;
    ArtistRecommendation artistInput;
    LabelRecommendation labelInput;
    TrackRecommendation trackInput;
    AlbumRecommendation albumUpdate;
    ArtistRecommendation artistUpdate;
    LabelRecommendation labelUpdate;
    TrackRecommendation trackUpdate;
    List<AlbumRecommendation> albumReturnList;
    List<ArtistRecommendation> artistReturnList;
    List<LabelRecommendation> labelReturnList;
    List<TrackRecommendation> trackReturnList;


    @Before
    public void setUp() throws Exception {
        setUpAlbumRepositoryMock();
        setUpArtistRecommendationRepositoryMock();
        setUpLabelRecommendationRepositoryMock();
        setUpTrackRecommendationRepositoryMock();

        service = new ServiceLayer(albumRepository, artistRepository, labelRepository, trackRepository);

    }

    private void setUpAlbumRepositoryMock() {
        albumRepository = mock(AlbumRecommendationRepository.class);
        albumInput= new AlbumRecommendation(1,2,false);
        albumReturn = new AlbumRecommendation(1,1,2,false);
        albumUpdate = new AlbumRecommendation(1,4,2,false);
        albumReturnList = new ArrayList<>();
        albumReturnList.add(albumReturn);


        doReturn(albumReturn).when(albumRepository).save(albumInput);
        doReturn(Optional.of(albumReturn)).when(albumRepository).findById(1);
        doReturn(albumReturnList).when(albumRepository).findAll();
        doReturn(albumUpdate).when(albumRepository).save(albumUpdate);
    }

    private void setUpArtistRecommendationRepositoryMock() {
        artistRepository = mock(ArtistRecommendationRepository.class);
        artistReturn = new ArtistRecommendation(2,3,true);
        artistInput = new ArtistRecommendation(1,2,3,true);
        artistUpdate = new ArtistRecommendation(1,6,3,true);
        artistReturnList = new ArrayList<>();
        artistReturnList.add(artistReturn);

        doReturn(artistReturn).when(artistRepository).save(artistInput);
        doReturn(Optional.of(artistReturn)).when(artistRepository).findById(1);
        doReturn(artistReturnList).when(artistRepository).findAll();
        doReturn(artistUpdate).when(artistRepository).save(artistUpdate);
    }

    private void setUpLabelRecommendationRepositoryMock() {
        labelRepository = mock(LabelRecommendationRepository.class);
        labelReturn = new LabelRecommendation( 4,2,false);
        labelInput = new LabelRecommendation(1, 4,2,false);
        labelUpdate = new LabelRecommendation(1, 8,2,false);
        labelReturnList = new ArrayList<>();
        labelReturnList.add(labelReturn);

        doReturn(labelReturn).when(labelRepository).save(labelInput);
        doReturn(Optional.of(labelReturn)).when(labelRepository).findById(1);
        doReturn(labelReturnList).when(labelRepository).findAll();
        doReturn(labelUpdate).when(labelRepository).save(labelUpdate);
    }

    private void setUpTrackRecommendationRepositoryMock(){
        trackRepository = mock(TrackRecommendationRepository.class);
        trackReturn = new TrackRecommendation(3,1,true);
        trackInput = new TrackRecommendation(5,3,1,true);
        trackUpdate = new TrackRecommendation(5,3,24,true);
        trackReturnList = new ArrayList<>();
        trackReturnList.add(trackReturn);

        doReturn(trackReturn).when(trackRepository).save(trackInput);
        doReturn(Optional.of(trackReturn)).when(trackRepository).findById(1);
        doReturn(trackReturnList).when(trackRepository).findAll();
        doReturn(trackUpdate).when(trackRepository).save(trackUpdate);
    }


    @Test
    public void findAllItems(){
        List<AlbumRecommendation> albumList = service.getAllAlbumRecommendation();
        List<ArtistRecommendation> artistList = service.getAllArtistRecommendation();
        List<LabelRecommendation> labelList = service.getAllLabelRecommendation();
        List<TrackRecommendation> trackList = service.getAllTrackRecommendation();

        assertEquals(1, albumList.size());
        assertEquals(1, artistList.size());
        assertEquals(1, labelList.size());
        assertEquals(1, trackList.size());
    }

    @Test
    public void findItemById(){
        assertEquals(labelReturn, service.getLabelRecommendationById(1));
        assertEquals(trackReturn, service.getTrackRecommendationById(1));
        assertEquals(albumReturn, service.getAlbumRecommendationById(1));
        assertEquals(artistReturn, service.getArtistRecommendationById(1));
    }

    @Test
    public void createItem(){
        assertEquals(labelReturn,service.createLabelRecommendation(labelInput));
        assertEquals(albumReturn,service.createAlbumRecommendation(albumInput));
        assertEquals(artistReturn,service.createArtistRecommendation(artistInput));
        assertEquals(trackReturn,service.createTrackRecommendation(trackInput));
    }

    @Test
    public void updateItem(){
        assertEquals(albumUpdate,service.updateAlbumRecommendation(albumUpdate));
        assertEquals(artistUpdate,service.updateArtistRecommendation(artistUpdate));
        assertEquals(labelUpdate,service.updateLabelRecommendation(labelUpdate));
        assertEquals(trackUpdate,service.updateTrackRecommendation(trackUpdate));
    }
}