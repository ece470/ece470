package com.hms.springbackendhms.appointment;

import com.hms.springbackendhms.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/appointment1")
public class AppointmentController {

    private  final AppointmentService appointmentService;
    private final PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping(value = "/appointments/{id}/{date}")
    public List<Appointment> AppointmentsAfterDatePatient(@PathVariable int id, @PathVariable String date) {
            appointmentService.saveAppoint(Appointment.builder()
                    .start_time(date)
                    .end_time(date)
                    .patient(patientService.findPatientByAmka(id).get())
                    .build());
            return appointmentService.getAppointments();
    }

    @GetMapping
    public List<Appointment> AppointmentsAfterDatePatient() {
        return appointmentService.getAppointments();
    }
}
