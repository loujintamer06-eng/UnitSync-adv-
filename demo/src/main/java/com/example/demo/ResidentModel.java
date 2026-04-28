package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "residents")
public class ResidentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resid;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "apt_number")
    private String aptNumber;
    private String status;
    private String email;
    private String phone;

    @Column(name = "move_in_date")
    private LocalDate moveInDate;

    public ResidentModel() {
    	
    }

	

	public ResidentModel(int resid, String firstName, String lastName, String username, String password,
			String aptNumber, String status, String email, String phone, LocalDate moveInDate) {
		super();
		this.resid = resid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.aptNumber = aptNumber;
		this.status = status;
		this.email = email;
		this.phone = phone;
		this.moveInDate = moveInDate;
	}

    

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAptNumber() {
		return aptNumber;
	}

	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(LocalDate moveInDate) {
		this.moveInDate = moveInDate;
	}
    

    
}
