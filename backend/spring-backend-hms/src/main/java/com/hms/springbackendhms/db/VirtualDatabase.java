package com.hms.springbackendhms.db;

import com.hms.springbackendhms.user.Doctor;
import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.user.Role;
import com.hms.springbackendhms.user.User;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VirtualDatabase {
    private static ArrayList<Patient> db = new ArrayList(
            /*List.of(
                    Patient
                            .builder()
                            .dob("2001")
                            .address("Vassani")
                            .amka("12345")
                            .afm("123")
                            .city("Volos")
                            .tel("123")
                            .password("123")
                            .build()
            )*/
    );
    private static ArrayList<Doctor> doctorsDB = new ArrayList<>(
            /*List.of(
                    Doctor
                            .builder()
                            .firstname("Tufts")
                            .lastname("Fillipou")
                            .email("fillipou@tufts.com")
                            .tel("123")
                            .amka("01130")
                            .afm("123")
                            .dob("2001")
                            .id(0)
                            .officeAddress("Boston")
                            .officeCity("Boston")
                            .password("123")
                            .role(Role.USER)
                            .build()
            )*/
    );
    public static void addPatient(Patient patient){
        db.add(patient);
    }

    public static Patient findByEmail(String email){
        for(Patient patient : db){
            if(patient.getEmail().equals(email)){
                return patient;
            }
        }
        return null;
    }

    public static Doctor findDoctorByEmail(String email){
        for(Doctor doctor : doctorsDB){
            if(doctor.getEmail().equals(email)){
                return doctor;
            }
        }
        return null;
    }

    public static boolean has(Patient patient) {
        for(Patient registeredPatient : db){
            if(registeredPatient.getEmail().equals(patient.getEmail())){
                return true;
            }
        }
        return false;
    }

    public static boolean hasDoctor(Doctor doctor) {
        for(Doctor registeredDoctor : doctorsDB){
            if(registeredDoctor.getEmail().equals(doctor.getEmail())){
                return true;
            }
        }
        return false;
    }

    public static void addDoctor(Doctor doctor) {
        doctorsDB.add(doctor);
    }

    public static boolean hasDoctor(String email){
        for(Doctor registeredDoctor : doctorsDB){
            if(registeredDoctor.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public static boolean hasPatient(String email){
        for(Patient registeredPatient : db){
            if(registeredPatient.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public static Doctor getDoctor(String email){
        for(Doctor doctor : doctorsDB){
            if(doctor.getEmail().equals(email)){
                return doctor;
            }
        }
        return null;
    }

    public static Patient getPatient(String email){
        for(Patient patient : db){
            if(patient.getEmail().equals(email)){
                return patient;
            }
        }
        return null;
    }

    public static Patient findPatientByAmka(String amka){
        for(Patient patient : db){
            if(patient.getAmka().equals(amka)){
                return patient;
            }
        }
        return null;
    }
}
