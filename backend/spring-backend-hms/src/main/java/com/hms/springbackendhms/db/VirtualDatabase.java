package com.hms.springbackendhms.db;

import com.hms.springbackendhms.user.Doctor;
import com.hms.springbackendhms.user.Patient;

import java.util.ArrayList;

public class VirtualDatabase {
    private static ArrayList<Patient> db = new ArrayList();
    private static ArrayList<Doctor> doctorsDB = new ArrayList<>();
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
}
