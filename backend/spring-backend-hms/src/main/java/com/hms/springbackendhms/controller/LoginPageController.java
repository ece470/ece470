package com.hms.springbackendhms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginPageController {

    @GetMapping
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Login Page");
    }
}
