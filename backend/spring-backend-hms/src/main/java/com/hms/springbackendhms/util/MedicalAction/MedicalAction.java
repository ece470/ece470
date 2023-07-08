package com.hms.springbackendhms.util.MedicalAction;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MedicalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String title;
    String details;

    @ManyToOne
    @JoinTable(name = "patient_MedicalAction",
            joinColumns = @JoinColumn(name = "MedicalAction_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "MedicaAction_appoint",
            joinColumns = @JoinColumn(name = "MedicalAction_id"),
            inverseJoinColumns = @JoinColumn(name = "appoint_id"))
    private Appointment appointment;

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
