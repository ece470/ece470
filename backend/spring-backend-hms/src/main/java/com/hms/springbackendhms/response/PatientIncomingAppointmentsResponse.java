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

    /*
    Fields

        1) Prescriptions
        2) Diagnoses
        3) Medical Actions

    must be empty
    */

    ArrayList<PatientAppointment> incomingAppointments;
}
