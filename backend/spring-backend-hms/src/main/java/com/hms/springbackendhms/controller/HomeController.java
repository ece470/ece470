package com.hms.springbackendhms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    /*@GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("Home Page");
    }
     */
    @GetMapping
    public String home(){
        return "index";
    }
}
