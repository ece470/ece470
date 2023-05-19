package com.hms.springbackendhms.auth;

import com.hms.springbackendhms.config.JwtService;
import com.hms.springbackendhms.db.VirtualDatabase;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.user.Role;
import com.hms.springbackendhms.user.User;
import com.hms.springbackendhms.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
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
                .dob(request.getPatientDob())
                .afm(request.getPatientAfm())
                .amka(request.getPatientAmka())
                .address(request.getPatientAddress())
                .city(request.getPatientCity())
                .build();

        if(!VirtualDatabase.has(patient)) {
            VirtualDatabase.addPatient(patient);
            var jwtToken = jwtService.generateToken(patient);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        System.out.println("User already exists");
        return null;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var patient = VirtualDatabase.findByEmail(request.getEmail());
        if(patient == null || !patient.getPassword().equals(request.getPassword())){
            return null;
        }
        var jwtToken = jwtService.generateToken(patient);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
