package com.hms.springbackendhms.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public boolean has(Patient patient) {
        Optional<Patient> existing = patientRepository.findPatientByEmail(patient.getEmail());
        return existing.isPresent();
    }

    public Optional<Patient> findByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    public Optional<Patient> findPatientByAmka(int amka){
        return patientRepository.findPatientByAmka(amka);
    }
}
