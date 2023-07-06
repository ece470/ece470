package com.hms.springbackendhms.appointment;

import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String from;
    private String to;
    private String title;
    private Long Hex_colored;

    @ManyToOne
    @JoinTable(name = "patient_appoint",
            joinColumns = @JoinColumn(name = "appoint_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private Patient patient;



    @ManyToOne
    @JoinTable(name = "doc_appoint",
            joinColumns = @JoinColumn(name = "appoint_id"),
            inverseJoinColumns = @JoinColumn(name = "doc_id"))
    private Doctor doctor;

    public Appointment(String start_time, String end_time, String title, Long hex_colored, Patient patient, Doctor doctor) {
        this.from = start_time;
        this.to = end_time;
        this.title = title;
        Hex_colored = hex_colored;
        this.patient = patient;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }
    public Long getHex_colored() {
        return Hex_colored;
    }

    public void setHex_colored(Long hex_colored) {
        Hex_colored = hex_colored;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTitle() {
        return title;
    }
}
