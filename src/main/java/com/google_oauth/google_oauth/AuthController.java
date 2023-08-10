package com.google_oauth.google_oauth;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
public class AuthController {
//
//    @Value("${spring.security.oauth2.client.registration.google.client-id}")
//    private String googleClientId;
//
//    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
//    private String googleClientSecret;
//xxxx
//    @Value("${spring.security.oauth2.client.registration.google.redirectUri}")
//    private String googleRedirectUri;
//
//    @Value("${spring.security.oauth2.client.registration.google.authorization-grant-type}")
//    private String googleAuthorizationGrantType;
//
//    @Value("${spring.security.oauth2.client.registration.google.scope}")
//    private String googleScope;


//    private final ClientRegistrationRepository clientRegistrationRepository;

//    public OAuthController(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }

//    public AuthController(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }

    @GetMapping("/test")
    public String test1() {
        System.out.println("Test");
        return "Text";

    }

    @PostMapping("/verify-google-token")
    public ResponseEntity<String> verifyGoogleToken(@RequestBody String idToken) {
        // Use a library or the Google API to verify the token
        // For example, using google-auth-library-java
        System.out.println("Step 1");

        // Sample code (this might require actual implementation):
        if (verifyToken(idToken)) {
            // Token is valid
            return ResponseEntity.ok("Token verified");
        } else {
            // Token is invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token verification failed");
        }
    }

    // This method should verify the token using the appropriate libraries/APIs
    private boolean verifyToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("your-client-id")) // Replace with your client ID
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                IdToken.Payload payload = idToken.getPayload();
                // You can verify additional claims here if needed
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
//
//    // This method should verify the token using the appropriate libraries/APIs
//    private boolean verifyToken(String idToken) {
//        // Your implementation here
//        return true; // Or false if verification fails
//    }
//
//    @GetMapping("login/oauth2/code/google")
//    public void startGoogleAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println("Authorization Code: " );
//        String redirectUri = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/oauth2/callback/google";
//
//        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("google");
//
//        String googleAuthorizationUri = clientRegistration.getProviderDetails().getAuthorizationUri() +
//                "?client_id=" + googleClientId +
//                "&redirect_uri=" + redirectUri +
//                "&response_type=code" +
//                "&scope=" + String.join(" ", clientRegistration.getScopes());
//
//        response.sendRedirect(googleAuthorizationUri);
//    }
//
//    @GetMapping("/oauth2/callback/google")
//    public void callbackGoogle(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String code = request.getParameter("code");
//        // Here you can exchange the authorization code for an access token and fetch user information as needed.
//        // This part involves making HTTP requests to the Google OAuth API.
//        // You would typically use a library like Spring Security OAuth or OAuth2 RestTemplate for this purpose.
//
//        // For simplicity, I'll just print the authorization code.
//        System.out.println("Authorization Code: " + code);
//
//        response.sendRedirect("/"); // Redirect to your desired page after authentication
//    }
//}
