package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.response.DoctorAppointmentsHistoryResponse;
import com.hms.springbackendhms.response.PatientIncomingAppointmentsResponse;
import com.hms.springbackendhms.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/restapi/patient_incoming_appointments")
@RequiredArgsConstructor
public class PatientIncomingAppointments {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public PatientIncomingAppointmentsResponse incomingAppointments(
            @CookieValue(name="token", defaultValue = "") String token
    ) {

        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (VirtualDatabase.hasPatient(userEmail)) {
                    // return the incoming
                    // appointments of patient
                    // ---------------------
                    // SELECT *
                    // FROM PatientAppointment
                    // where mail = userEmail
                    // AND date > now()


                    ArrayList<Diagnosis> diagnoses = new ArrayList<>();

                    ArrayList<Prescription> prescriptions = new ArrayList<>();

                    ArrayList<MedicalAction> medicalActions = new ArrayList<>();


                    ArrayList<PatientAppointment> appointments = new ArrayList<>();
                    appointments.add(
                            PatientAppointment
                                    .builder()
                                    .doctorFirstname("Anastasia")
                                    .doctorLastname("Mallikopoulou")
                                    .date(new Date())
                                    .doctorSpecialisation("Cardiologist")
                                    .diagnoses(diagnoses)
                                    .medicalActions(medicalActions)
                                    .prescriptions(prescriptions)
                                    .build()
                    );

                    return PatientIncomingAppointmentsResponse
                            .builder()
                            .incomingAppointments(appointments)
                            .build();
                }

                return null;
            }
        }

        return null;
    }
}
