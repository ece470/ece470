package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.request.AddPrescriptionRequest;
import com.hms.springbackendhms.response.PatientResponse;
import com.hms.springbackendhms.response.StatusResponse;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.util.Medicine;
import com.hms.springbackendhms.util.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/restapi/add_prescription")
@RequiredArgsConstructor
public class AddPrescription {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public StatusResponse addPrescription(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody AddPrescriptionRequest request
    )
    {
        System.out.println("Request-amka: " + request.getAmka());
        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (VirtualDatabase.hasDoctor(userEmail)) {

                    String userAmka = request.getAmka();

                    Patient patient = VirtualDatabase.findPatientByAmka(userAmka);
                    if(patient == null){
                        return StatusResponse
                                .builder()
                                .status(StatusResponse.FAIL)
                                .build();
                    }
                    Prescription prescription = Prescription
                            .builder()
                            .useUntil(new Date(/*request.getUseUntil()*/))
                            .description(request.getDescription())
                            .medicine(Medicine
                                    .builder()
                                    .name(request.getMedicine()).
                                    build()
                            )
                            .build();

                    // add the prescription on database
                    // for the user WHERE amka = userAmka

                    return StatusResponse
                            .builder()
                            .status(StatusResponse.SUCCESS)
                            .build();
                }
            }
        }
        return null;
    }
}
