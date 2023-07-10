package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.response.PatientIncomingAppointmentsResponse;
import com.hms.springbackendhms.util.PatientAppointment;
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
import java.util.List;
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
    ) { //TODO:throws ParseException {

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

                    List<PatientAppointment> appointments = new ArrayList<>();

                    for (Appointment appointment : appointmentList) {
                        //TODO:Date s1 =  new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(appointmentList.get(i).getstart_time());
                        appointments.add(
                                PatientAppointment
                                        .builder()
                                        .doctorFirstname(appointment.getDoctor().getFirstname())
                                        .doctorLastname(appointment.getDoctor().getLastname())
                                        .date(new Date())
                                        .doctorSpecialisation(appointment.getDoctor().getSpecialization())
                                        .diagnoses(appointment.getDiagnosisList())
                                        .medicalActions(appointment.getMedicalActionList())
                                        .prescriptions(appointment.getPrescriptionList())
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
