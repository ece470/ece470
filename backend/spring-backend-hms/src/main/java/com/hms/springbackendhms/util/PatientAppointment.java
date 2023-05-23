package com.hms.springbackendhms.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientAppointment {
    String doctorFirstname;
    String doctorLastname;
    String doctorSpecialisation;
    Date date;
    ArrayList<Prescription> prescriptions;
    ArrayList<Diagnosis> diagnoses;
    ArrayList<MedicalAction> medicalActions;
}
