package com.hms.springbackendhms.doctor;

import com.hms.springbackendhms.appointment.Appointment;
import com.hms.springbackendhms.user.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Doctor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address_office;
    private int afm_office;
    //private int amka;
    private String city_office;
    @Temporal(TemporalType.DATE)
    private Date dob_office;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String tel_office;
    private Role role;

    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointmentList;

    public Doctor(String address_office, int afm_office, String city_office, Date dob_office, String email, String firstname, String lastname, String password, String tel_office, Role role) {
        this.address_office = address_office;
        this.afm_office = afm_office;
        this.city_office = city_office;
        this.dob_office = dob_office;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.tel_office = tel_office;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getAddress_office() {
        return address_office;
    }

    public int getAfm_office() {
        return afm_office;
    }

    public String getCity_office() {
        return city_office;
    }

    public Date getDob_office() {
        return dob_office;
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

    public String getTel_office() {
        return tel_office;
    }

    public Role getRole() {
        return role;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
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
