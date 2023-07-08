package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.request.AddPrescriptionRequest;
import com.hms.springbackendhms.response.StatusResponse;
import com.hms.springbackendhms.util.Medicine.Medicine;
import com.hms.springbackendhms.util.Prescription.Prescription;
import com.hms.springbackendhms.util.Prescription.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/add_prescription")
@RequiredArgsConstructor
public class AddPrescription {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;

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

                if (doctorService.findDoctorByEmail(userEmail).isPresent()) {

                    String userAmka = request.getAmka();

                    Optional<Patient> patient = patientService.findPatientByAmka(Integer.parseInt(userAmka));
                    if(patient.isEmpty()){
                        return StatusResponse
                                .builder()
                                .status(StatusResponse.FAIL)
                                .build();
                    }
                    Prescription prescription = Prescription
                            .builder()
                            .useUntil(new Date(/*request.getUseUntil()*/).toString())
                            .description(request.getDescription())
                            .medicine(List.of(Medicine
                                    .builder()
                                    .name(request.getMedicine()).
                                    build()
                            ))
                            .patient(patient.get())
                            .build();

                    prescriptionService.updatePrescription(prescription);
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
