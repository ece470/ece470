package com.hms.springbackendhms.db;

import com.hms.springbackendhms.user.Patient;
import com.hms.springbackendhms.user.Role;
import com.hms.springbackendhms.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VirtualDatabase {
    private static ArrayList<Patient> db = new ArrayList();
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

    public static boolean has(Patient patient) {
        for(Patient registeredPatient : db){
            if(registeredPatient.getEmail().equals(patient.getEmail())){
                return true;
            }
        }
        return false;
    }
}
