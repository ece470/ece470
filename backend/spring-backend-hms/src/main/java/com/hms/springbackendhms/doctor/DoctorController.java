package com.hms.springbackendhms.doctor;

import com.hms.springbackendhms.appointment.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {

    private final DoctorService doctorService;


    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    List<Doctor> getDoctors() {
        return doctorService.getDoctors();
    }

    @PostMapping
    public void registerNewDoctor(@RequestBody Doctor doctor) {
        doctorService.addNewDoctor(doctor);
    }

    @GetMapping(value = "/doctors_appoint/{email}")
    public void getAppoints(@PathVariable String email) {
        System.out.println(email);
        //Integer doctorId = doctorService.getId(email);
        List<Appointment> appointmentList = doctorService.getAppointments(email, Date.valueOf(LocalDate.now()).toString());
        System.out.println(appointmentList.toString());
    }
}
