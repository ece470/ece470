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
import java.util.Optional;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(AppointmentRepository repository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        return args -> {
            Doctor doctor11 = new Doctor("Boston",
                    123,
                    "Boston",
                    Date.valueOf(LocalDate.of(2001,Month.AUGUST,14)),
                    "fillipou@tufts.com",
                    "Tufts",
                    "Fillipou",
                    "123",
                    "123",
                    Role.USER);

            Patient patient11 = new Patient("Vassani",
                    123,
                    12345,
                    "Volos",
                    Date.valueOf(LocalDate.of(2001,Month.AUGUST,14)),
                    "something@at.com",
                    "Someone",
                    "Somebody",
                    "123",
                    "123",
                    Role.USER);
//                            .dob("2001")
//                            .address("Vassani")
//                            .amka("12345")
//                            .afm("123")
//                            .city("Volos")
//                            .tel("123")
//                            .password("123")
//                            .build()

//                    firstname("Tufts")
//                            .lastname("Fillipou")
//                            .email("fillipou@tufts.com")
//                            .tel("123")
//                            .amka("01130")
//                            .afm("123")
//                            .dob("2001")
//                            .id(0)
//                            .officeAddress("Boston")
//                            .officeCity("Boston")
//                            .password("123")
//                            .role(Role.USER)

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
            patientRepository.saveAll(List.of(patient,patient11));

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
            doctorRepository.saveAll(List.of(doctor,doctor11));
            Optional<Patient> patient1 = patientRepository.findPatientByAmka(1234);
            if(patient1.isPresent()) {

                Appointment appointment = new Appointment(
                        Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)).toString(), Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)).toString(),
                        "Ioylianoy 10",
                        1234L,
                        patientRepository.findPatientByAmka(1234).get(),
                        doctorRepository.findDoctorByAfm(1234)
                );
                repository.saveAll(List.of(appointment));
            }
        };
    }
}