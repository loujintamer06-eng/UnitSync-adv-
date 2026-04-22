package com.residential.model;

import jakarta.persistence.*;

@Entity
@Table(name = "workers")
public class Worker extends person {

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String department;

    @Column
    private double salary;

    @Column
    private String shift; // e.g., "Morning", "Evening", "Night"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkerStatus status;

    public enum WorkerStatus {
        ACTIVE, INACTIVE, ON_LEAVE
    }

    // Constructors
    public Worker() {}

    public Worker(String name, String email, String phone, String password,
                  String jobTitle, String department, double salary, String shift) {
        super(name, email, phone, password);
        this.jobTitle = jobTitle;
        this.department = department;
        this.salary = salary;
        this.shift = shift;
        this.status = WorkerStatus.ACTIVE;
    }

    // Getters & Setters
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public WorkerStatus getStatus() { return status; }
    public void setStatus(WorkerStatus status) { this.status = status; }
}
