package com.hms.springbackendhms.patient;


import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.user.Role;
import com.hms.springbackendhms.util.diagnosis.Diagnosis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Patient implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private int afm;
    private int amka;
    private String city;
    private Date dob;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String tel;

//    @ElementCollection
//    private List<String> diseases;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    ArrayList<Appointment> appointmentList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    ArrayList<Diagnosis> diagnosisArrayList;

    @Transient
    private int age;

    @Enumerated(EnumType.STRING)
    private Role role;
    public Patient( String address, int afm, int amka, String city, Date dob, String email, String firstname, String lastname, String password, String tel, Role role) {
        this.address = address;
        this.afm = afm;
        this.amka = amka;
        this.city = city;
        this.dob = dob;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Integer getAfm() {
        return afm;
    }

    public Integer getAmka() {
        return amka;
    }

    public String getCity() {
        return city;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTel() {
        return tel;
    }

    public int getAge() {
        return LocalDate.now().getYear()-dob.toLocalDate().getYear();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

