package com.hms.springbackendhms.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/patients")
public class PatientsController {

    private final PatientService patientService;

    @Autowired
    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }



    @GetMapping
    public ArrayList<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping(consumes = "application/json")
    public void registerNewPatient(@RequestBody Patient patient)
    {
        System.out.println(patient);
        //this.patientService.addNewPatient(patient);
    }

}
