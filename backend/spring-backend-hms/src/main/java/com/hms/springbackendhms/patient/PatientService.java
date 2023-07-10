package com.hms.springbackendhms.patient;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
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

    public Optional<Patient> findPatientByEmail(String email) {
        return patientRepository.findPatientByEmail(email);
    }

    public Optional<Patient> findPatientByAmka(int amka){
        return patientRepository.findPatientByAmka(amka);
    }

    public List<Appointment> appointmentsAfterDatePatient(Patient patient, String date) {
        return appointmentRepository.findAfterDateforPatient(patient,date);
    }

    public List<Appointment> appointmentsBeforeDatePatient(Patient patient, String date) {
        return appointmentRepository.findBeforeDateforPatient(patient,date);
    }

}
