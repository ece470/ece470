package com.hms.springbackendhms.appointment;//package com.example.test_db_connection.appointment;
//
//import com.hms.springbackendhms.doctor.DoctorRepository;
//import com.example.test_db_connection.patient.PatientRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class  AppointmentConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(AppointmentRepository repository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
//        return args -> {
//            Appointment patient = new Appointment(
//                    Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),
//                    "Ioylianoy 10",
//                    1234L,
//                                    patientRepository.findPatientByAmka(1234),
//                                    doctorRepository.findDoctorByAfm(1234)
//            );
//            repository.saveAll(List.of(patient));
//        };
//    }
//}