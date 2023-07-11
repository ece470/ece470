package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.response.PatientAppointmentsHistoryResponse;
import com.hms.springbackendhms.response.PatientIncomingAppointmentsResponse;
import com.hms.springbackendhms.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/restapi/patient_appointments_history")
@RequiredArgsConstructor
public class PatientAppointmentsHistory {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    public PatientAppointmentsHistoryResponse history(
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
                    // return the history
                    // appointments of patient
                    // ---------------------
                    // SELECT *
                    // FROM PatientAppointment
                    // where mail = userEmail
                    // AND date < now()


                    ArrayList<Diagnosis> diagnoses1 = new ArrayList<>();
                    diagnoses1.add(
                            Diagnosis
                                    .builder()
                                    .details("High blood pressure")
                                    .build()
                    );

                    ArrayList<Prescription> prescriptions1 = new ArrayList<>();

                    ArrayList<MedicalAction> medicalActions1 = new ArrayList<>();

                    ArrayList<Diagnosis> diagnoses2 = new ArrayList<>();

                    ArrayList<Prescription> prescriptions2 = new ArrayList<>();
                    prescriptions2.add(
                            Prescription
                                    .builder()
                                    .description("lorem ipsum")
                                    .useUntil(new Date())
                                    .medicine(
                                            Medicine.builder().name("Depon").id("4321").build()
                                    )
                                    .build()
                    );
                    ArrayList<MedicalAction> medicalActions2 = new ArrayList<>();

                    ArrayList<Diagnosis> diagnoses3 = new ArrayList<>();
                    ArrayList<Prescription> prescriptions3 = new ArrayList<>();
                    ArrayList<MedicalAction> medicalActions3 = new ArrayList<>();
                    medicalActions3.add(
                            MedicalAction
                                    .builder()
                                    .title("Emvolio")
                                    .details("covid 19")
                                    .build()
                    );

                    ArrayList<PatientAppointment> history = new ArrayList<>();
                    history.add(
                            PatientAppointment
                                    .builder()
                                    .doctorFirstname("Anastasia")
                                    .doctorLastname("Mallikopoulou")
                                    .date(new Date())
                                    .doctorSpecialisation("Cardiologist")
                                    .diagnoses(diagnoses1)
                                    .medicalActions(medicalActions1)
                                    .prescriptions(prescriptions1)
                                    .build()
                    );

                    history.add(
                            PatientAppointment
                                    .builder()
                                    .doctorFirstname("Kalli")
                                    .doctorLastname("Yannikoglou")
                                    .date(new Date())
                                    .doctorSpecialisation("Paediatrician")
                                    .diagnoses(diagnoses2)
                                    .medicalActions(medicalActions2)
                                    .prescriptions(prescriptions2)
                                    .build()
                    );

                    history.add(
                            PatientAppointment
                                    .builder()
                                    .doctorFirstname("Nikos")
                                    .doctorLastname("Koukos")
                                    .date(new Date())
                                    .doctorSpecialisation("Orthopedic")
                                    .diagnoses(diagnoses3)
                                    .medicalActions(medicalActions3)
                                    .prescriptions(prescriptions3)
                                    .build()
                    );

                    return PatientAppointmentsHistoryResponse
                            .builder()
                            .history(history)
                            .build();
                }

                return null;
            }
        }

        return null;
    }
}
