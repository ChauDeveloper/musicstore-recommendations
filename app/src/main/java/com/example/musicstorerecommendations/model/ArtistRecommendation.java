package com.example.musicstorerecommendations.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist_recommendation")
public class ArtistRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_recommendation_id")
    private Integer id;
    @NotNull(message = "You must enter an artist ID")
    @Column(name = "artist_id")
    private Integer artistId;
    @NotNull(message = "You must enter a user ID")
    @Column(name = "user_id")
    private Integer userId;
    @NotNull(message = "You must choose true or false if you like this artist")
    private Boolean liked;



    public ArtistRecommendation() {}
    public ArtistRecommendation(Integer artistId, Integer userId, Boolean liked) {
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }
    public ArtistRecommendation(Integer id, Integer artistId, Integer userId, Boolean liked) {
        this.id = id;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistRecommendation that = (ArtistRecommendation) o;
        return Objects.equals(id, that.id) && Objects.equals(artistId, that.artistId) && Objects.equals(userId, that.userId) && Objects.equals(liked, that.liked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artistId, userId, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecommendation{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
