package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.response.PatientAppointmentsHistoryResponse;
import com.hms.springbackendhms.util.*;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/patient_appointments_history")
@RequiredArgsConstructor
public class PatientAppointmentsHistory {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PatientService patientService;

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

                Optional<Patient> patient = patientService.findPatientByEmail(userEmail);
                if (patient.isPresent()) {
                    // return the history
                    // appointments of patient
                    // ---------------------
                    // SELECT *
                    // FROM PatientAppointment
                    // where mail = userEmail
                    // AND date < now()
                    patientService.appointmentsBeforeDatePatient(patient.get().getId(), LocalDate.now().toString());

                    ArrayList<Diagnosis> diagnoses = new ArrayList<>();
                    diagnoses.add(
                            Diagnosis
                                    .builder()
                                    .details("High blood pressure")
                                    .build()
                    );

                    ArrayList<Prescription> prescriptions = new ArrayList<>();
                    prescriptions.add(
                            Prescription
                                    .builder()
                                    .medicine(Medicine.builder().id("h783hdn2").name("MedName").build())
                                    .description("lorem ipsum")
                                    .useUntil(new Date())
                                    .build()
                    );

                    ArrayList<MedicalAction> medicalActions = new ArrayList<>();
                    medicalActions.add(
                            MedicalAction
                                    .builder()
                                    .title("some title")
                                    .details("details of medical action")
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
                                    .diagnoses(diagnoses)
                                    .medicalActions(medicalActions)
                                    .prescriptions(prescriptions)
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
