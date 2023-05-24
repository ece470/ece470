package com.hms.springbackendhms.doctor;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        return doctorRepository.findAll();
    }

    public void addNewDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Integer getId(String email) {
        Optional<Doctor> doctor = doctorRepository.findDoctorByEmail(email);
        return doctor.map(Doctor::getId).orElse(0);
    }

    public List<Appointment> getAppointments(Integer id, Date date) {
        return appointmentRepository.findAppointmentsByDoctor(id, date);
    }
}
