package com.hms.springbackendhms.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterRequest {
    private String doctorAddress;
    private String doctorAfm;
    private String doctorAmka;
    private String doctorCity;
    private String doctorDob;
    private String doctorEmail;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorPassword;
    private String doctorTel;
}
