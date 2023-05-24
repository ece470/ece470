package com.hms.springbackendhms.controller;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medical_action")
@RequiredArgsConstructor
public class MedicalActionPageController {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DoctorService doctorService;

    @GetMapping
    public String newMedicalAction(
            @CookieValue(name = "token", defaultValue = "") String token
    )
    {
        if(token.isBlank()){
            return "register";
            //return home page
        }
        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {
            //System.out.println("Main Page: " + userEmail);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {

                if(doctorService.findDoctorByEmail(userEmail).isPresent()){
                    System.out.println("Return new medical action");
                    return "newMedicalAction";
                }

            } else {
                // return home page
                return "register";
            }


        }
        return "register";
        // return home page
    }
}
