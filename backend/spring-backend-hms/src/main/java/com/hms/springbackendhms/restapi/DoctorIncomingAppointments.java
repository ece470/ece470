package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.response.DoctorIncomingAppointmentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restapi/doctor_incoming_appointments")
@RequiredArgsConstructor
public class DoctorIncomingAppointments {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DoctorService doctorService;
    //TODO:private final AppointmentService appointmentService;

    @GetMapping
    public DoctorIncomingAppointmentsResponse incomingAppointments(
            @CookieValue(name = "token", defaultValue = "") String token
    )
    {
        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (doctorService.findDoctorByEmail(userEmail).isPresent()) {
                    // return the incoming
                    // appointments of doctor
                    // ---------------------
                    // SELECT *
                    // FROM DoctorAppointments
                    // where mail = userEmail
                    // AND date > now()
                    List<Appointment> incoming = doctorService.getAppointments(userEmail, LocalDate.now().toString());

//                    List<DoctorAppointment> appointments = new List<>();
//                    appointments.add(DoctorAppointment.builder()
//                            .patientLastname("Lagomatis")
//                            .patientFirstname("Ilias")
//                            .date(new Date())
//                            .build());
//
//                    appointments.add(DoctorAppointment.builder()
//                            .patientLastname("Mallikopoulou")
//                            .patientFirstname("Anastasia")
//                            .date(new Date())
//                            .build());

                    return DoctorIncomingAppointmentsResponse
                            .builder()
                            .incomingAppointments(incoming)
                            .build();
                }

                return null;
            }
        }

        return null;
    }
}
