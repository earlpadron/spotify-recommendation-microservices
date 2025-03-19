package io.github.earlpadron.authservice;


import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;
    private final SpotifyService spotifyService;

    public CustomOAuth2UserService(UserRepository userRepository, SpotifyService spotifyService) {
        this.userRepository = userRepository;
        this.spotifyService = spotifyService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Get basic user details from Spotify
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String spotifyUserId = oAuth2User.getName(); // Spotify user ID
        String displayName = oAuth2User.getAttribute("display_name");
        String email = oAuth2User.getAttribute("email");

        // Fetch additional data using access token
        String accessToken = userRequest.getAccessToken().getTokenValue();
        String listeningHistory = spotifyService.fetchListeningHistory(accessToken);
        String playlists = spotifyService.fetchUserPlaylists(accessToken);

        // Save or update user data in the database
        User user = userRepository.findBySpotifyUserId(spotifyUserId)
                .orElse(new User());
        user.setSpotifyUserId(spotifyUserId);
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.setListeningHistory(listeningHistory);
        user.setPlaylists(playlists);
        user.setCreatedAt(java.time.LocalDateTime.now());
        user.setAccessToken(accessToken);
        userRepository.save(user);

        return oAuth2User;
    }




}
