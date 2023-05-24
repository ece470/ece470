package com.hms.springbackendhms.controller;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainPageController {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    @GetMapping
    public String mainPage(
            @CookieValue(name = "token", defaultValue = "") String token
    )
    {
        if(token.isBlank()){
            return "register";
            //return home page
        }
        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {
            System.out.println("Main Page: " + userEmail);


            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {
                // access database to get info about the user
                // and return main page

                if(doctorService.findDoctorByEmail(userEmail).isPresent()){
                    return "homePageDoct";
                }
                if(patientService.findByEmail(userEmail).isPresent()){
                    return "homePagePatients";
                }
                return "index1";

            } else {
                // return home page
                return "register";
            }


        }
        return "register";
        // return home page
    }
}
