package oopclasses;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int resId;
    private double amount;
    private String status; 
    private LocalDate dueDate;
    
    public Payment(int paymentId, int resId, double amount, String status, LocalDate dueDate) {
        this.paymentId = paymentId;
        this.resId = resId;
        this.amount = amount;
        this.status = status;
        this.dueDate = dueDate;
    }

    public int getPaymentId() { 
    	return paymentId; }
    public int getResId() { 
    	return resId; }
    public double getAmount() { 
    	return amount; }
    public String getStatus() { 
    	return status; }
    public LocalDate getDueDate() { 
    	return dueDate; }

    public void setAmount(double amount) { 
    	this.amount = amount; }
    public void setStatus(String status) { 
    	this.status = status; }
    public void setDueDate(LocalDate dueDate) {
    	this.dueDate = dueDate; }
    
    public double getPaymentAmount() { 
    	return amount; }

    public boolean isPaid() { 
    	return status.equalsIgnoreCase("paid"); }

    public boolean isOverdue() {
        return !isPaid() && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return "Payment{id=" + paymentId + ", resId=" + resId +
               ", amount=" + amount + ", status=" + status +
               ", dueDate=" + dueDate + "}";
    }
}