package com.hms.springbackendhms.auth;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    )
    {
        System.out.println("Incoming register request");
        System.out.println("mail: " + request.getEmail());
        AuthenticationResponse authenticationResponse = service.register(request);
        if(authenticationResponse == null){
            return ResponseEntity.ok("This email is already registered");
        }
        String token = authenticationResponse.getToken();
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(true)
                .domain("localhost")
                .path("/")
                .maxAge(1000)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    )
    {
        AuthenticationResponse authenticationResponse = service.authenticate(request);

        if(authenticationResponse == null){
            return ResponseEntity.ok("Authentication Failed");
        }

        String token = authenticationResponse.getToken();
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(true)
                .domain("localhost")
                .path("/")
                .maxAge(1000)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
