package com.hms.springbackendhms.doctor;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<Doctor> getDoctors() {
        return  doctorRepository.findAll();
    }

    public void addNewDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Integer getId(String email) {
        Optional<Doctor> doctor = doctorRepository.findDoctorByEmail(email);
        return doctor.map(Doctor::getId).orElse(0);
    }

    public List<Appointment> getAppointments(String email, String date) {
        Optional<Doctor> doctor = doctorRepository.findDoctorByEmail(email);
        return doctor.map(value -> appointmentRepository.findAppointmentIncomingDoctor(doctor.get(), date)).orElse(null);
    }

    public List<Appointment> getAppointmentsHistory(String email, String date) {
        Optional<Doctor> doctor = doctorRepository.findDoctorByEmail(email);
        return doctor.map(value -> appointmentRepository.findAppointmentHistoryByDoctor(value, LocalDate.now().toString())).orElse(null);
    }

    public Optional<Doctor> findDoctorByEmail(String email) {

        System.out.println(doctorRepository.findDoctorByEmail(email).isPresent());
        return doctorRepository.findDoctorByEmail(email);
    }

    public boolean has(Doctor doctor) {
        return doctorRepository.findDoctorByEmail(doctor.getEmail()).isPresent();
    }
}
