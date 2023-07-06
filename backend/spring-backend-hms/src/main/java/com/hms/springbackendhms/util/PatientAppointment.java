package com.hms.springbackendhms.util;

import com.hms.springbackendhms.util.diagnosis.Diagnosis;
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
