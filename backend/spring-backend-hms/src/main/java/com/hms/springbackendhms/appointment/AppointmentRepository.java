package com.hms.springbackendhms.appointment;

import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query("Select s from Appointment s where s.patient = ?1 and s.start_time < ?2")
    List<Appointment> findBeforeDateforPatient(Patient id, String date);

    @Query("Select s from Appointment s where s.patient = ?1 and s.start_time >= ?2")
    List<Appointment> findAfterDateforPatient(Patient id, String date);

    @Query("Select s from Appointment s where s.patient = ?1")
    List<Appointment> findAppointmentByPatient(Patient id);

    @Query("Select s from Appointment s where s.doctor = ?1 and s.start_time < ?2")
    List<Appointment> findAppointmentHistoryByDoctor(Doctor id, String date);
    @Query("Select s from Appointment s where s.doctor = ?1 and s.start_time >= ?2")
    List<Appointment> findAppointmentIncomingDoctor(Doctor id, String date);
}
