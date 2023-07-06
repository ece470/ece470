package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.request.FindPatientRequest;
import com.hms.springbackendhms.response.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/restapi/find_patient_by_amka")
@RequiredArgsConstructor
public class FindPatientByAmka {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @PostMapping
    public PatientResponse findPatient(
            @CookieValue(name="token", defaultValue = "") String token,
            @RequestBody FindPatientRequest request
    )
    {
        System.out.println("New request on findPatientByAmka");
        System.out.println("Amka: " + request.getAmka());
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
                    if(patient.isPresent()){
                        return PatientResponse
                                .builder()
                                .afm(patient.get().getAfm().toString())
                                .amka(patient.get().getAmka().toString())
                                .city(patient.get().getCity())
                                .tel(patient.get().getTel())
                                .email(patient.get().getEmail())
                                .address(patient.get().getAddress())
                                .firstname(patient.get().getFirstname())
                                .lastname(patient.get().getLastname())
                                .build();
                    }
                }
            }
        }
        return null;
    }
}
