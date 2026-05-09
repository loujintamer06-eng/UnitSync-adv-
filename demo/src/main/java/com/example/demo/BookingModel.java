package com.example.demo;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class BookingModel {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "booking_id")
	    private int bookingId;

	    @Column(name = "resident_id")
	    private int residentId;

	    @Column(name = "resident_name")
	    private String residentName;

	    @Column(name = "amenity_name")
	    private String amenityName;

	    @Column(name = "amount")
	    private double amount;

	    @Column(name = "booking_date")
	    private String bookingDate;

	    @Column(name = "booking_time")
	    private String bookingTime;

	    // "Pending" / "Confirmed" / "Cancelled"
	    @Column(name = "status")
	    private String status;

	    @Column(name = "created_at")
	    private String createdAt;

	    // ===== Default Constructor =====
	    public BookingModel() {}

	    // ===== Getters & Setters =====
	    public int getBookingId() { return bookingId; }
	    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

	    public int getResidentId() { return residentId; }
	    public void setResidentId(int residentId) { this.residentId = residentId; }

	    public String getResidentName() { return residentName; }
	    public void setResidentName(String residentName) { this.residentName = residentName; }

	    public String getAmenityName() { return amenityName; }
	    public void setAmenityName(String amenityName) { this.amenityName = amenityName; }

	    public double getAmount() { return amount; }
	    public void setAmount(double amount) { this.amount = amount; }

	    public String getBookingDate() { return bookingDate; }
	    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

	    public String getBookingTime() { return bookingTime; }
	    public void setBookingTime(String bookingTime) { this.bookingTime = bookingTime; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public String getCreatedAt() { return createdAt; }
	    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
	}