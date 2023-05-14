package com.hms.springbackendhms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginPageController {

    @GetMapping
    public String login(){
        return "login";
    }
}
