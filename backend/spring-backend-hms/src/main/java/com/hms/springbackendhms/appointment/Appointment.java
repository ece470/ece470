package com.hms.springbackendhms.appointment;

import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.util.MedicalAction.MedicalAction;
import com.hms.springbackendhms.util.Prescription.Prescription;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String start_time;
    private String end_time;
    private String title;
    private Long Hex_colored;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(name = "patient_appoint",
            joinColumns = @JoinColumn(name = "appoint_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;



    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(name = "doc_appoint",
            joinColumns = @JoinColumn(name = "appoint_id"),
            inverseJoinColumns = @JoinColumn(name = "doc_id"))
    private Doctor doctor;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment")
    private List<Diagnosis> diagnosisList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment")
    private List<MedicalAction> medicalActionList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment")
    private List<Prescription> prescriptionList;



    public Appointment(String start_time, String end_time, String title, Long hex_colored, Patient patient, Doctor doctor) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        Hex_colored = hex_colored;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }
//    public Long getHex_colored() {
//        return Hex_colored;
//    }
//
//    public void setHex_colored(Long hex_colored) {
//        Hex_colored = hex_colored;
//    }
//TODO:
//    public String getstart_time() {
//        return start_time;
//    }
//
//    public String getend_time() {
//        return end_time;
//    }

    public String getTitle() {
        return title;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<MedicalAction> getMedicalActionList() {
        return medicalActionList;
    }

    public List<Prescription> getPrescriptionList() {
        return prescriptionList;
    }
}
