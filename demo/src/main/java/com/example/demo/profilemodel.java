package com.example.demo;
import jakarta.persistence.*;

@Entity
@Table(name = "residents")
public class profilemodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resid")
    private int residentId;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "apt_number")
    private String apartment;

    @Column(name = "status")
    private String status;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // ===== Constructors =====
    public profilemodel() {}

    public profilemodel(int residentId, String firstname, String apartment, String status, String username, String password) {
        this.residentId = residentId;
        this.firstname = firstname;
        this.apartment = apartment;
        this.status = status;
        this.username = username;
        this.password = password;
    }

    // ===== Getters & Setters =====
    public int getResidentId() { return residentId; }
    public void setResidentId(int residentId) { this.residentId = residentId; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getApartment() { return apartment; }
    public void setApartment(String apartment) { this.apartment = apartment; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}