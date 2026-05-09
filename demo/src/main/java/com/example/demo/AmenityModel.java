package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "amenities")
public class AmenityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_id")
    private int amenityId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "availability")
    private String availability;

    @Column(name = "available_from")
    private String availableFrom;

    @Column(name = "admin_role")
    private String adminRole;
    
    public AmenityModel() {}

	public AmenityModel(int amenityId, String name, String location, String availability, String availableFrom,
			String adminRole) {
		super();
		this.amenityId = amenityId;
		this.name = name;
		this.location = location;
		this.availability = availability;
		this.availableFrom = availableFrom;
		this.adminRole = adminRole;
	}

	public int getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(String availableFrom) {
		this.availableFrom = availableFrom;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

}