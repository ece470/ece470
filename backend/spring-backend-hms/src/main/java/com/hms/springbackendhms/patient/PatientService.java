package com.hms.springbackendhms.patient;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentRepository;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import com.hms.springbackendhms.util.diagnosis.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository,DiagnosisRepository diagnosisRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    public ArrayList<Patient> getPatients() {
        return new ArrayList<>(patientRepository.findAll());
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public boolean has(Patient patient) {
        Optional<Patient> existing = patientRepository.findPatientByEmail(patient.getEmail());
        return existing.isPresent();
    }

    public Optional<Patient> findPatientByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    public Optional<Patient> findPatientByAmka(int amka){
        return patientRepository.findPatientByAmka(amka);
    }

    public ArrayList<Appointment> appointmentsAfterDatePatient(int id, String date) {
        return appointmentRepository.findAfterDateforPatient(id,date);
    }

    public ArrayList<Appointment> appointmentsBeforeDatePatient(int id, String date) {
        return appointmentRepository.findBeforeDateforPatient(id,date);
    }

}
