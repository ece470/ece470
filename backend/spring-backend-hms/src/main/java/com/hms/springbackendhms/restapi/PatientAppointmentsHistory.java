package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.config.JwtService;
//import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.response.PatientAppointmentsHistoryResponse;
import com.hms.springbackendhms.util.*;
import com.hms.springbackendhms.util.MedicalAction.MedicalAction;
import com.hms.springbackendhms.util.MedicalAction.MedicalActionService;
import com.hms.springbackendhms.util.Medicine.Medicine;
import com.hms.springbackendhms.util.Prescription.Prescription;
import com.hms.springbackendhms.util.Prescription.PrescriptionService;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import com.hms.springbackendhms.util.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/restapi/patient_appointments_history")
@RequiredArgsConstructor
public class PatientAppointmentsHistory {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PatientService patientService;
    private final DiagnosisService diagnosisService;
    private final PrescriptionService prescriptionService;
    private final MedicalActionService medicalActionService;

    @GetMapping
    public PatientAppointmentsHistoryResponse history(
            @CookieValue(name="token", defaultValue = "") String token
    ) {

        if(token.isBlank()){
            return null;
        }

        String userEmail = jwtService.extractUsername(token);
        if(userEmail != null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(token, userDetails)) {

                Optional<Patient> patient = patientService.findPatientByEmail(userEmail);
                if (patient.isPresent()) {
                    // return the history
                    // appointments of patient
                    // ---------------------
                    // SELECT *
                    // FROM PatientAppointment
                    // where mail = userEmail
                    // AND date < now()
                    List<Appointment> appointmentList = patientService.appointmentsBeforeDatePatient(patient.get(), LocalDate.now().toString());

                    if(appointmentList.isEmpty()) {
                        return PatientAppointmentsHistoryResponse
                                .builder()
                                .history(null)
                                .build();
                    }
                    List<Diagnosis> diagnoses = new ArrayList<>();
                    diagnoses.add(
                            Diagnosis
                                    .builder()
                                    .details("High blood pressure")
                                    .patient(patient.get())
                                    .appointment(appointmentList.get(0))
                                    .build()
                    );

                    diagnosisService.addListDiagnosis(diagnoses);

                    List<Prescription> prescriptions = new ArrayList<>();
                    prescriptions.add(
                            Prescription
                                    .builder()
                                    .medicine(List.of(Medicine.builder().id("h783hdn2").name("MedName").build()))
                                    .description("lorem ipsum")
                                    .useUntil(new Date().toString())
                                    .patient(patient.get())
                                    .appointment(appointmentList.get(0))
                                    .build()
                    );

                    prescriptionService.addListPrescription(prescriptions);

                    List<MedicalAction> medicalActions = new ArrayList<>();
                    medicalActions.add(
                            MedicalAction
                                    .builder()
                                    .title("some title")
                                    .details("details of medical action")
                                    .patient(patient.get())
                                    .appointment(appointmentList.get(0))
                                    .build()
                    );

                    medicalActionService.addListMedicalAction(medicalActions);

                    List<PatientAppointment> history = new ArrayList<>();

                    history.add(
                            PatientAppointment
                                    .builder()
                                    .doctorFirstname(appointmentList.get(0).getDoctor().getFirstname())
                                    .doctorLastname(appointmentList.get(0).getDoctor().getLastname())
                                    .date(new Date())
                                    .doctorSpecialisation(appointmentList.get(0).getDoctor().getSpecialization())
                                    .diagnoses(diagnoses)
                                    .medicalActions(medicalActions)
                                    .prescriptions(prescriptions)
                                    .build()
                    );

                    return PatientAppointmentsHistoryResponse
                            .builder()
                            .history(history)
                            .build();
                }

                return null;
            }
        }

        return null;
    }
}
