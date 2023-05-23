package com.hms.springbackendhms.response;

import com.hms.springbackendhms.util.PatientAppointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientIncomingAppointmentsResponse {
    ArrayList<PatientAppointment> incomingAppointments;
}
