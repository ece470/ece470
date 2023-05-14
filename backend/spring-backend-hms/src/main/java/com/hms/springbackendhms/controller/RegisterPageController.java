package com.hms.springbackendhms.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterPageController {

    @GetMapping
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("Register Page");
    }
}
