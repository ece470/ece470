package com.hms.springbackendhms.patient;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.appointment.AppointmentRepository;
import com.hms.springbackendhms.doctor.Doctor;


import com.hms.springbackendhms.doctor.DoctorRepository;
import com.hms.springbackendhms.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(AppointmentRepository repository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        return args -> {
            Patient patient = new Patient("Ioylianoy 10",
                    1234,
                    1234,
                    "Volos",
                    Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),
                    "chris.l.prapas@gmail.com",
                    "Christos",
                    "Prapas",
                    "1234",
                    "6971599002",
                    Role.ADMIN
            );
            patientRepository.saveAll(List.of(patient));

            Doctor doctor = new Doctor("Ioylianoy 10",
                    1234,
                    "Volos",
                    Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),
                    "chris.l.prapas@gmail.com",
                    "Christos",
                    "Prapas",
                    "1234",
                    "6971599002",
                    Role.ADMIN
            );
            doctorRepository.saveAll(List.of(doctor));

            Appointment appointment = new Appointment(
                    Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)), Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),
                    "Ioylianoy 10",
                    1234L,
                    patientRepository.findPatientByAmka(1234),
                    doctorRepository.findDoctorByAfm(1234)
            );
            repository.saveAll(List.of(appointment));
        };
    }
}