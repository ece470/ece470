package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.request.AddDiagnosisRequest;
import com.hms.springbackendhms.request.AddPrescriptionRequest;
import com.hms.springbackendhms.response.StatusResponse;

import com.hms.springbackendhms.util.Diagnosis;
import com.hms.springbackendhms.util.Medicine;
import com.hms.springbackendhms.util.Prescription;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/add_diagnosis")
@RequiredArgsConstructor
public class AddDiagnosis {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DoctorService doctorService;
    private final PatientService patientService;

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

                if (doctorService.findDoctorByEmail(userEmail).isPresent()) {

                    String userAmka = request.getAmka();

                    Optional<Patient> patient = patientService.findPatientByAmka(Integer.parseInt(userAmka));
                    if(patient.isEmpty()){
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
