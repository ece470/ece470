package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.response.PatientIncomingAppointmentsResponse;
import com.hms.springbackendhms.util.*;
import com.hms.springbackendhms.util.MedicalAction.MedicalAction;
import com.hms.springbackendhms.util.Prescription.Prescription;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/patient_incoming_appointments")
@RequiredArgsConstructor
public class PatientIncomingAppointments {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PatientService patientService;

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

                Optional<Patient> patient = patientService.findPatientByEmail(userEmail);
                if (patient.isPresent()) {
                    // return the incoming
                    // appointments of patient
                    // ---------------------
                    // SELECT *
                    // FROM PatientAppointment
                    // where mail = userEmail
                    // AND date > now()
                    patientService.appointmentsAfterDatePatient(patient.get(), LocalDate.now().toString());

                    List<Diagnosis> diagnoses = new ArrayList<>();

                    List<Prescription> prescriptions = new ArrayList<>();

                    List<MedicalAction> medicalActions = new ArrayList<>();


                    List<PatientAppointment> appointments = new ArrayList<>();
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
