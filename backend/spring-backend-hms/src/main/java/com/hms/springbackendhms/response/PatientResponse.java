package com.hms.springbackendhms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private String address;
    private String afm;
    private String amka;
    private String city;
    private String email;
    private String firstname;
    private String lastname;
    private String tel;
}
