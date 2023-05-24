package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.request.AddDiagnosisRequest;
import com.hms.springbackendhms.request.ExecuteMedicalActionRequest;
import com.hms.springbackendhms.response.StatusResponse;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.util.Diagnosis;
import com.hms.springbackendhms.util.MedicalAction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restapi/execute_medical_action")
@RequiredArgsConstructor
public class ExecuteMedicalAction {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public StatusResponse executeMedicalAction(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody ExecuteMedicalActionRequest request
    )
    {
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

                    MedicalAction medicalAction = MedicalAction
                            .builder()
                            .title(request.getTitle())
                            .details(request.getDetails())
                            .build();

                    // add the medicalAction on database
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
