package com.example.demo;
import jakarta.persistence.*;
@Entity
@Table(name = "payments")
public class RPaymentModel {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "payment_id")
	    private int paymentId;

	    @Column(name = "res_id")
	    private int residentId;

	    @Column(name = "amenity")
	    private String amenity;

	    @Column(name = "amount")
	    private double amount;

	    @Column(name = "due_date")
	    private String dueDate;

	    // "Pending" / "Paid"
	    @Column(name = "status")
	    private String status;

	    // "cash" / "card"
	    @Column(name = "pay_method")
	    private String payMethod;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "card_number")
	    private String cardNumber;

	    @Column(name = "paid_at")
	    private String paidAt;

	    // ===== Default Constructor =====
	    public RPaymentModel() {}

	    // ===== Getters & Setters =====
	    public int getPaymentId() { return paymentId; }
	    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

	    public int getResidentId() { return residentId; }
	    public void setResidentId(int residentId) { this.residentId = residentId; }

	    public String getAmenity() { return amenity; }
	    public void setAmenity(String amenity) { this.amenity = amenity; }

	    public double getAmount() { return amount; }
	    public void setAmount(double amount) { this.amount = amount; }

	    public String getDueDate() { return dueDate; }
	    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public String getPayMethod() { return payMethod; }
	    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

	    public String getFirstName() { return firstName; }
	    public void setFirstName(String firstName) { this.firstName = firstName; }

	    public String getLastName() { return lastName; }
	    public void setLastName(String lastName) { this.lastName = lastName; }

	    public String getCardNumber() { return cardNumber; }
	    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

	    public String getPaidAt() { return paidAt; }
	    public void setPaidAt(String paidAt) { this.paidAt = paidAt; }
	}