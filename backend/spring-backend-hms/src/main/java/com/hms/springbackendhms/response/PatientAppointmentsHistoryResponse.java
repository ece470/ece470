package com.hms.springbackendhms.response;

import com.hms.springbackendhms.util.PatientAppointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientAppointmentsHistoryResponse {
    List<PatientAppointment> history;
}
