package com.hms.springbackendhms.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query("Select s from Appointment s where s.patient = ?1 and s.start_time <= ?2")
    List<Appointment> findByDateforPatient(int id, Date date);

    @Query("Select s from Appointment s where s.patient = ?1")
    List<Appointment> findAppointmentByPatient(int id);

    @Query("Select s from Appointment s where s.doctor = ?1 and s.start_time >= ?2")
    List<Appointment> findAppointmentsByDoctor(int id, Date date);
}
