package com.hms.springbackendhms.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public ArrayList<Appointment> getAppointments() {
        return new ArrayList<Appointment>(appointmentRepository.findAll());
    }
    public boolean saveAppoint(Appointment appointment) {
        Appointment check = appointmentRepository.save(appointment);
        return appointmentRepository.findById(check.getId()).isPresent();
    }
}
