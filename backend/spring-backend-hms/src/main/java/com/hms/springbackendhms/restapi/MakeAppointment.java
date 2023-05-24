package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.request.ExecuteMedicalActionRequest;
import com.hms.springbackendhms.request.MakeAppointmentRequest;
import com.hms.springbackendhms.response.StatusResponse;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.util.Appointment;
import com.hms.springbackendhms.util.MedicalAction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restapi/make_appointment")
@RequiredArgsConstructor
public class MakeAppointment {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

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

                if (VirtualDatabase.hasPatient(userEmail)) {

                    Appointment appointment =
                            Appointment
                                    .builder()
                                    .from(request.getFrom())
                                    .to(request.getTo())
                                    .build();

                    // add the appointment on database
                    // for the user WHERE email = userEmail
                    boolean addedSuccesfully = false;
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
