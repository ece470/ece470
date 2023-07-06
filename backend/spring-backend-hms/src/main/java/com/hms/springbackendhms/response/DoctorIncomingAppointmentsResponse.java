package com.hms.springbackendhms.response;

import com.hms.springbackendhms.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorIncomingAppointmentsResponse {

    ArrayList<Appointment> incomingAppointments;
}
