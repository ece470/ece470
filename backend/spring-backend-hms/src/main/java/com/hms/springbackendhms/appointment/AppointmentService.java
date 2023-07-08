package com.hms.springbackendhms.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
    public boolean saveAppoint(Appointment appointment) {
        Appointment check = appointmentRepository.save(appointment);
        return appointmentRepository.findById(check.getId()).isPresent();
    }
}
