package com.hms.springbackendhms.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    @Query("Select s from Patient s where s.amka = ?1")
    Optional<Patient> findPatientByAmka(int amka);

    @Query("Select s from Patient s where s.afm = ?1")
    Optional<Patient> findPatientByAfm(int afm);

    @Query("Select s from Patient s where s.address = ?1")
    Optional<Patient> findPatientByAddress(String address);

    @Query("Select s from Patient s where s.city = ?1")
    Optional<Patient> findPatientByCity(int city);

    @Query("Select s from Patient s where s.dob = ?1")
    Optional<Patient> findPatientByDob(LocalDate dob);

    @Query("Select s from Patient s where s.email = ?1")
    Optional<Patient> findPatientByEmail(String email);

    @Query("Select s from Patient s where s.firstname = ?1")
    Optional<Patient> findPatientByFirstname(String firstname);

    @Query("Select s from Patient s where s.lastname = ?1")
    Optional<Patient> findPatientByLastname(String lastname);

    @Query("Select s from Patient s where s.tel = ?1")
    Optional<Patient> findPatientByTel(String tel);
}
