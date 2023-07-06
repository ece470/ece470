package com.hms.springbackendhms.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query("Select s from Appointment s where s.patient = ?1 and s.from < ?2")
    ArrayList<Appointment> findBeforeDateforPatient(int id, String date);

    @Query("Select s from Appointment s where s.patient = ?1 and s.from >= ?2")
    ArrayList<Appointment> findAfterDateforPatient(int id, String date);

    @Query("Select s from Appointment s where s.patient = ?1")
    ArrayList<Appointment> findAppointmentByPatient(int id);

    @Query("Select s from Appointment s where s.doctor = ?1 and s.from < ?2")
    ArrayList<Appointment> findAppointmentHistoryByDoctor(int id, String date);
    @Query("Select s from Appointment s where s.doctor = ?1 and s.from >= ?2")
    ArrayList<Appointment> findAppointmentIncomingDoctor(int id, String date);
}
