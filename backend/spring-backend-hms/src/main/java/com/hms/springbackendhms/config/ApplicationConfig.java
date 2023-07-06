package com.hms.springbackendhms.config;


import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.user.User1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final User1Repository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    /*
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> VirtualDatabase.findByEmail(username);


           // return username -> repository.findByEmail(username)
           //     .orElseThrow(() -> new UsernameNotFoundException("User not found"));


    }
    */
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
          if(doctorService.findDoctorByEmail(username).isPresent()){
              return doctorService.findDoctorByEmail(username).get();
          }
          if(patientService.findPatientByEmail(username).isPresent()){
              return patientService.findPatientByEmail(username).get();
          }
          return null;
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
