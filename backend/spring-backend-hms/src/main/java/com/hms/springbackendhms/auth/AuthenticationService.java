package com.hms.springbackendhms.auth;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.doctor.Doctor;
import com.hms.springbackendhms.doctor.DoctorService;
import com.hms.springbackendhms.patient.Patient;
import com.hms.springbackendhms.patient.PatientService;
import com.hms.springbackendhms.user.Role;
import com.hms.springbackendhms.user.User1;
import com.hms.springbackendhms.user.User1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    //private final User1Repository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PatientService patientService;
    private final DoctorService doctorService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User1.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        /*if(!VirtualDatabase.has(user)) {
            VirtualDatabase.addUser(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }

         */
        //System.out.println("User already exists");
        return null;
    }

    public AuthenticationResponse patientRegister(PatientRegisterRequest request) {
        var patient = Patient.builder()
                .firstname(request.getPatientFirstName())
                .lastname(request.getPatientLastName())
                .email(request.getPatientEmail())
                .password(passwordEncoder.encode(request.getPatientPassword()))
                .role(Role.USER)
                .tel(request.getPatientTel())
                .dob(Date.valueOf(request.getPatientDob()))
                .afm(Integer.parseInt(request.getPatientAfm()))
                .amka(Integer.parseInt(request.getPatientAmka()))
                .address(request.getPatientAddress())
                .city(request.getPatientCity())
                .build();

        if(!patientService.has(patient)) {
            patientService.addNewPatient(patient);
            var jwtToken = jwtService.generateToken(patient);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        System.out.println("User already exists");
        return null;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        System.out.println("Reached Authentication1");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("Reached Authentication2");

        Optional<Patient> patient = patientService.findPatientByEmail(request.getEmail());
        if(patient.isEmpty()){
            Optional<Doctor> doctor = doctorService.findDoctorByEmail(request.getEmail());
            if(doctor.isEmpty()){
                System.out.println("Auth Failed");
                return null;
            }
            System.out.println("Reached doctor success");
            var jwtToken = jwtService.generateToken(doctor.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        System.out.println("Reached Patient success");
        var jwtToken = jwtService.generateToken(patient.get());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse doctorRegister(DoctorRegisterRequest request) {
        var doctor = Doctor.builder()
                .firstname(request.getDoctorFirstName())
                .lastname(request.getDoctorLastName())
                .email(request.getDoctorEmail())
                .password(passwordEncoder.encode(request.getDoctorPassword()))
                .role(Role.USER)
                .tel_office(request.getDoctorTel())
                .dob_office(Date.valueOf(request.getDoctorDob()))
                .afm_office(Integer.parseInt(request.getDoctorAfm()))
                //.amka(request.getDoctorAmka())
                .address_office(request.getDoctorAddress())
                .city_office(request.getDoctorCity())
                .build();

        if(!doctorService.has(doctor)) {
            doctorService.addNewDoctor(doctor);
            var jwtToken = jwtService.generateToken(doctor);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        System.out.println("Doctor already exists");
        return null;
    }
}
