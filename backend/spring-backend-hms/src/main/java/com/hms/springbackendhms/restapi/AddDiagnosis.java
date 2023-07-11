package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.request.AddDiagnosisRequest;
import com.hms.springbackendhms.request.AddPrescriptionRequest;
import com.hms.springbackendhms.response.StatusResponse;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.util.Diagnosis;
import com.hms.springbackendhms.util.Medicine;
import com.hms.springbackendhms.util.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/restapi/add_diagnosis")
@RequiredArgsConstructor
public class AddDiagnosis {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public StatusResponse addDiagnosis(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody AddDiagnosisRequest request
    )
    {
        System.out.println(request.getAmka() + " " + request.getDiagnosis());
        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                if (VirtualDatabase.hasDoctor(userEmail)) {

                    String userAmka = request.getAmka();
                    System.out.println("Amka add diagnosis: " + userAmka);
                    Patient patient = VirtualDatabase.findPatientByAmka(userAmka);
                    if(patient == null){
                        return StatusResponse
                                .builder()
                                .status(StatusResponse.FAIL)
                                .build();
                    }
                    Diagnosis diagnosis = Diagnosis
                            .builder()
                            .details(request.getDiagnosis())
                            .build();

                    // add the diagnosis on database
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
