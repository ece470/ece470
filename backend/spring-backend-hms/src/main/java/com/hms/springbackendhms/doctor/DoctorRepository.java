package com.hms.springbackendhms.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

//    @Query("Select s from Doctor s where s.amka = ?1")
//    Optional<Doctor> findDoctorByAmka(int amka);

    @Query("Select s from Doctor s where s.afm_office = ?1")
    Doctor findDoctorByAfm(int afm);

    @Query("Select s from Doctor s where s.address_office = ?1")
    Optional<Doctor> findDoctorByAddress(String address);

    @Query("Select s from Doctor s where s.city_office = ?1")
    Optional<Doctor> findDoctorByCity(int city);

    @Query("Select s from Doctor s where s.dob_office = ?1")
    Optional<Doctor> findDoctorByDob(LocalDate dob);

    @Query("Select s from Doctor s where s.email = ?1")
    Optional<Doctor> findDoctorByEmail(String email);

    @Query("Select s from Doctor s where s.firstname = ?1")
    Optional<Doctor> findDoctorByFirstname(String firstname);

    @Query("Select s from Doctor s where s.lastname = ?1")
    Optional<Doctor> findDoctorByLastname(String lastname);

    @Query("Select s from Doctor s where s.tel_office = ?1")
    Optional<Doctor> findDoctorByTel(String tel);
}
