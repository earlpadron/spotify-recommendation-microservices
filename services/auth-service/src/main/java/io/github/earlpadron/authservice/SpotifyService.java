package io.github.earlpadron.authservice;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class SpotifyService {

    private final RestClient restClient;

    public SpotifyService(RestClient restClient) {
        this.restClient = restClient;
    }

    // Fetch user's listening history (top tracks)
    public String fetchListeningHistory(String accessToken) {
        return restClient.get()
                .uri("https://api.spotify.com/v1/me/top/tracks")
                .headers(headers -> {
                    headers.setBearerAuth(accessToken);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .body(String.class);
    }

    // Fetch user's playlists
    public String fetchUserPlaylists(String accessToken) {
        return restClient.get()
                .uri("https://api.spotify.com/v1/me/playlists")
                .headers(headers -> {
                    headers.setBearerAuth(accessToken);
                    headers.setContentType(MediaType.APPLICATION_JSON);
                })
                .retrieve()
                .body(String.class);
    }



}
