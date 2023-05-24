package com.hms.springbackendhms.doctor;//package com.example.test_db_connection.doctor;
//
//import com.hms.springbackendhms.user.Role;
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
//public class DoctorConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(DoctorRepository repository) {
//        return args -> {
//            Doctor doctor = new Doctor("Ioylianoy 10",
//                    1234,
//                    "Volos",
//                    Date.valueOf(LocalDate.of(2001, Month.NOVEMBER, 20)),
//                    "chris.l.prapas@gmail.com",
//                    "Christos",
//                    "Prapas",
//                    "1234",
//                    "6971599002",
//                    Role.ADMIN
//            );
//            repository.saveAll(List.of(doctor));
//        };
//    }
//}
