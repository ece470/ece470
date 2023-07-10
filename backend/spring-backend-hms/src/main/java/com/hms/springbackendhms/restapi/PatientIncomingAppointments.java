package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.appointment.Appointment;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ) throws ParseException {

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
                    List<Appointment> appointmentList = patientService.appointmentsAfterDatePatient(patient.get(), LocalDate.now().toString());
                    if(appointmentList.isEmpty()) {
                        return PatientIncomingAppointmentsResponse
                                .builder()
                                .incomingAppointments(null)
                                .build();
                    }

                    List<Diagnosis> diagnoses = patient.get().getDiagnosisList();

                    List<Prescription> prescriptions = patient.get().getPrescriptionList();

                    List<MedicalAction> medicalActions = patient.get().getMedicalActionList();


                    List<PatientAppointment> appointments = new ArrayList<>();

                    for(int i=0;i<appointmentList.size();i++) {
                        //TODO:Date s1 =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(appointmentList.get(i).getstart_time());
                        appointments.add(
                                PatientAppointment
                                        .builder()
                                        .doctorFirstname(appointmentList.get(i).getDoctor().getFirstname())
                                        .doctorLastname(appointmentList.get(i).getDoctor().getLastname())
                                        .date(new Date())
                                        .doctorSpecialisation(appointmentList.get(i).getDoctor().getSpecialization())
                                        .diagnoses(appointmentList.get(i).getDiagnosisList())
                                        .medicalActions(appointmentList.get(i).getMedicalActionList())
                                        .prescriptions(appointmentList.get(i).getPrescriptionList())
                                        .build()
                        );
                    }


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
