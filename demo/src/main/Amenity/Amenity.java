package com.residential.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amenities")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmenityStatus status;

    @Column
    private String operatingHours; // e.g., "8:00 AM - 10:00 PM"

    public enum AmenityStatus {
        AVAILABLE, UNDER_MAINTENANCE, CLOSED
    }

    // Constructors
    public Amenity() {}

    public Amenity(String name, String description, String location, int capacity, String operatingHours) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.capacity = capacity;
        this.operatingHours = operatingHours;
        this.status = AmenityStatus.AVAILABLE;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public AmenityStatus getStatus() { return status; }
    public void setStatus(AmenityStatus status) { this.status = status; }

    public String getOperatingHours() { return operatingHours; }
    public void setOperatingHours(String operatingHours) { this.operatingHours = operatingHours; }
}
