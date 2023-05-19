package com.hms.springbackendhms.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequest {
    private String patientAddress;
    private String patientAfm;
    private String patientAmka;
    private String patientCity;
    private String patientDob;
    private String patientEmail;
    private String patientFirstName;
    private String patientLastName;
    private String patientPassword;
    private String patientTel;
}
