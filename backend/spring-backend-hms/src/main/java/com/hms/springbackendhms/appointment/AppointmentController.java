//package com.hms.springbackendhms.appointment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping(path = "/appointments")
//public class AppointmentController {
//
//    private  final AppointmentService appointmentService;
//
//    @Autowired
//    public AppointmentController(AppointmentService appointmentService) {
//        this.appointmentService = appointmentService;
//    }
//
////    @GetMapping(value = "/appointments/{id}/{date}")
////    public List<Appointment> AppointmentsAfterDatePatient(@PathVariable int id,@PathVariable Date date) {
////        return appointmentService.AppointmentsAfterDatePatient(id, date);
////    }
//
//    @GetMapping
//    public ArrayList<Appointment> AppointmentsAfterDatePatient() {
//        return appointmentService.getAppointments();
//    }
//}
