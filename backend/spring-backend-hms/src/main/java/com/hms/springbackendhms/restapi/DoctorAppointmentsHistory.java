package com.hms.springbackendhms.restapi;

import com.hms.springbackendhms.response.DoctorAppointmentsHistoryResponse;
import com.hms.springbackendhms.util.DoctorAppointment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/restapi/doctor_appointments_history")
@RequiredArgsConstructor
public class DoctorAppointmentsHistory {

    @GetMapping
    public DoctorAppointmentsHistoryResponse history() {
        ArrayList<DoctorAppointment> appointments = new ArrayList<>();
        appointments.add(DoctorAppointment.builder()
                        .patientLastname("Lagomatis")
                        .patientFirstname("Ilias")
                        .date(new Date())
                        .build());

        appointments.add(DoctorAppointment.builder()
                .patientLastname("Mallikopoulou")
                .patientFirstname("Anastasia")
                .date(new Date())
                .build());

        return DoctorAppointmentsHistoryResponse.builder()
                .history(appointments)
                .build();
    }
}
