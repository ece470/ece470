package com.hms.springbackendhms.util.diagnosis;

import com.hms.springbackendhms.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table
@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String details;

    @ManyToOne
    @JoinTable(name = "patient_diagnosis",
            joinColumns = @JoinColumn(name = "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;

    public Diagnosis(String details, Patient patient) {
        this.details = details;
        this.patient = patient;
    }
    public String getDetails() {
        return details;
    }
}
