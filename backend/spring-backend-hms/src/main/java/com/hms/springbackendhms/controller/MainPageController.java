package com.hms.springbackendhms.controller;

import com.hms.springbackendhms.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainPageController {
    private final JwtService jwtService;
    @GetMapping
    public ResponseEntity<String> mainPage(
            @CookieValue(name = "token", defaultValue = "") String token
    )
    {
        if(token.isBlank()){
            return ResponseEntity.ok("Disconnected");
        }
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok("Hello from secure endpoint, user: " + email);
    }
}
