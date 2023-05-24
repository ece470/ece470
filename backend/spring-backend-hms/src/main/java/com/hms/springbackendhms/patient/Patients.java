package com.hms.springbackendhms.patient;//package com.example.test_db_connection.user;
//
//import jakarta.persistence.*;
//
//import java.sql.Date;
//import java.util.List;
//
//@Entity
//@Table
//public class Patients {
//    @Id
//    private long AMKA;
//
//    private String Forename;
//    private String Lastname;
//    private String address;
//    private long Tel;
//
//    private String email;
//
//    private Date DateofBirth;
//    @ElementCollection
//    private List<String> diseases;
//
//    @Transient
//    private int age;
//
//    public Patients(long AMKA, String forename, String lastname, String address, long tel, String email, Date dateofBirth) {
//        this.AMKA = AMKA;
//        this.Forename = forename;
//        this.Lastname = lastname;
//        this.address = address;
//        this.Tel = tel;
//        this.email = email;
//        this.DateofBirth = dateofBirth;
//    }
//
//    public Patients() {
//    }
//
//    public long getAMKA() {
//        return AMKA;
//    }
//
//    public String getForename() {
//        return Forename;
//    }
//
//    public String getLastname() {
//        return Lastname;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public long getTel() {
//        return Tel;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Date getDateofBirth() {
//        return DateofBirth;
//    }
//
//    public List<String> getDiseases() {
//        return diseases;
//    }
//
//    public int getAge() {
//        long seconds = this.DateofBirth.getSeconds();
//        age = (int) (seconds/31536000);
//        return age;
//    }
//
//    @Override
//    public String toString() {
//        return "Patients{" +
//                "AMKA=" + AMKA +
//                ", Forename='" + Forename + '\'' +
//                ", Lastname='" + Lastname + '\'' +
//                ", address='" + address + '\'' +
//                ", Tel=" + Tel +
//                ", email='" + email + '\'' +
//                ", DateofBirth=" + DateofBirth +
//                ", diseases=" + diseases +
//                ", age=" + age +
//                '}';
//    }
//
//}
