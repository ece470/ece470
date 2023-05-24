package com.hms.springbackendhms.appointment;

import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.patient.Patient;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date start_time;
    @Temporal(TemporalType.DATE)
    private Date end_time;
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

    public Appointment(Date start_time, Date end_time, String title, Long hex_colored, Patient patient, Doctor doctor) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        Hex_colored = hex_colored;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment() {

    }

    public Long getHex_colored() {
        return Hex_colored;
    }

    public void setHex_colored(Long hex_colored) {
        Hex_colored = hex_colored;
    }

    public Date getstart_time() {
        return start_time;
    }

    public Date getend_time() {
        return end_time;
    }

    public String getTitle() {
        return title;
    }
}
