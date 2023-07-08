package com.hms.springbackendhms.util;

import com.hms.springbackendhms.util.MedicalAction.MedicalAction;
import com.hms.springbackendhms.util.Prescription.Prescription;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    List<Prescription> prescriptions;
    List<Diagnosis> diagnoses;
    List<MedicalAction> medicalActions;
}
