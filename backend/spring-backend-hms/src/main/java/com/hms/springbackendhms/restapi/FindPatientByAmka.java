package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.request.FindPatientRequest;
import com.hms.springbackendhms.response.PatientIncomingAppointmentsResponse;
import com.hms.springbackendhms.response.PatientResponse;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.util.Diagnosis;
import com.hms.springbackendhms.util.MedicalAction;
import com.hms.springbackendhms.util.PatientAppointment;
import com.hms.springbackendhms.util.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/restapi/find_patient_by_amka")
@RequiredArgsConstructor
public class FindPatientByAmka {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public PatientResponse findPatient(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody FindPatientRequest request
    )
    {
        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (VirtualDatabase.hasDoctor(userEmail)) {
                    String userAmka = request.getAmka();

                    Patient patient = VirtualDatabase.findPatientByAmka(userAmka);
                    if(patient != null){
                        return PatientResponse
                                .builder()
                                .afm(patient.getAfm())
                                .amka(patient.getAmka())
                                .city(patient.getCity())
                                .tel(patient.getTel())
                                .email(patient.getEmail())
                                .address(patient.getAddress())
                                .firstname(patient.getFirstname())
                                .lastname(patient.getLastname())
                                .build();
                    }
                }
            }
        }
        return null;
    }
}
