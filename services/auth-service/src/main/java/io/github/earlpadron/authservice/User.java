package io.github.earlpadron.authservice;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotifyUserId;
    private String displayName;
    private String email;

    @Lob
    private String listeningHistory;

    @Lob
    private String playlists;
    private String accessToken;

    private LocalDateTime createdAt;

    public User(Long id, String spotifyUserId, String displayName, String email, String listeningHistory, String playlists, String accessToken, LocalDateTime createdAt) {
        this.id = id;
        this.spotifyUserId = spotifyUserId;
        this.displayName = displayName;
        this.email = email;
        this.listeningHistory = listeningHistory;
        this.playlists = playlists;
        this.accessToken = accessToken;
        this.createdAt = createdAt;
    }

    public User() {

    }

    public String getSpotifyUserId() {
        return spotifyUserId;
    }

    public void setSpotifyUserId(String spotifyUserId) {
        this.spotifyUserId = spotifyUserId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getListeningHistory() {
        return listeningHistory;
    }

    public void setListeningHistory(String listeningHistory) {
        this.listeningHistory = listeningHistory;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
