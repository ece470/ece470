package com.hms.springbackendhms.restapi;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hms.springbackendhms.response.HistoryResponse;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/restapi/doctor_appointments_history")
@RequiredArgsConstructor
public class DoctorAppointmentsHistory {

    @GetMapping
    public HistoryResponse history()
    {
        return HistoryResponse.builder()
                .fname("ilias")
                .lname("lagomatis")
                .build();
    }
}
