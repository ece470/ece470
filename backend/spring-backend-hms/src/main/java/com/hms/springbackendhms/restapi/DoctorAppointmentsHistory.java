package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.response.DoctorAppointmentsHistoryResponse;
import com.hms.springbackendhms.util.DoctorAppointment;
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
@RequestMapping("/restapi/doctor_appointments_history")
@RequiredArgsConstructor
public class DoctorAppointmentsHistory {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @GetMapping
    public DoctorAppointmentsHistoryResponse history(
            @CookieValue(name = "token", defaultValue = "") String token
    ) {
        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (VirtualDatabase.hasDoctor(userEmail)) {
                    // return the history of doctor
                    // ---------------------
                    // SELECT *
                    // FROM DoctorAppointments
                    // where mail = userEmail
                    // AND date < now()

                    ArrayList<DoctorAppointment> appointments = new ArrayList<>();
                    appointments.add(DoctorAppointment.builder()
                            .patientLastname("Lagomatis")
                            .patientFirstname("Ilias")
                            .date(new Date())
                            .build());

                    appointments.add(DoctorAppointment.builder()
                            .patientLastname("Mallikopoulou")
                            .patientFirstname("Anastasia")
                            .date(new Date())
                            .build());

                    return DoctorAppointmentsHistoryResponse.builder()
                            .history(appointments)
                            .build();
                }

                return null;
            }
        }

        return null;
    }
}
