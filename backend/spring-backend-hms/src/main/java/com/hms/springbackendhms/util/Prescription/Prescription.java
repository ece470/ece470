package com.hms.springbackendhms.util.Prescription;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.util.Medicine.Medicine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "prescription")
    List<Medicine> medicine;

    String description;
    String useUntil;

    @ManyToOne
    @JoinTable(name = "patient_diagnosis",
            joinColumns = @JoinColumn(name = "diagnosis_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;

    @ManyToOne
    @JoinTable(name = "Prescription_appoint",
            joinColumns = @JoinColumn(name = "Prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "appoint_id"))
    private Appointment appointment;

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public String getDescription() {
        return description;
    }

    public void updateMedicine(List<Medicine> medicine) {
        this.medicine.addAll(medicine);
    }

    public String getUseUntil() {
        return useUntil;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
