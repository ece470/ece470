package com.hms.springbackendhms.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterPageController {

    @GetMapping
    public String register(){
        return "register";
    }
}
