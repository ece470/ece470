package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentService;
import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.request.MakeAppointmentRequest;
import com.hms.springbackendhms.response.StatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/restapi/make_appointment")
@RequiredArgsConstructor
public class MakeAppointment {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping
    public StatusResponse makeAppointment(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody MakeAppointmentRequest request
    )
    {
        System.out.println(request.getFrom() + " - " + request.getTo());

        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                Optional<Patient> patient = patientService.findPatientByEmail(userEmail);
                if (patient.isPresent()) {

                    Appointment appointment =
                            Appointment
                                    .builder()
                                    .start_time(request.getFrom())
                                    .end_time(request.getTo())
                                    .patient(patient.get())
                                    .build();

                    boolean addedSuccesfully = appointmentService.saveAppoint(appointment);
                    // add the appointment on database
                    // for the user WHERE email = userEmail
                    if(addedSuccesfully) {
                        return StatusResponse
                                .builder()
                                .status(StatusResponse.SUCCESS)
                                .build();
                    }
                    return StatusResponse
                            .builder()
                            .status(StatusResponse.FAIL)
                            .build();
                }
            }
        }
        return null;
    }
}
