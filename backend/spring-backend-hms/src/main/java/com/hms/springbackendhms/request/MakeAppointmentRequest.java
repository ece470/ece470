package com.hms.springbackendhms.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeAppointmentRequest {
    public String date;
    public String from;
    public String to;
}
