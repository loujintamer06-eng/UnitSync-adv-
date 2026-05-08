package Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workerId;
    private String unit;
    private String issue;
    private LocalDate date;
    private String status;

    public Task() {}
    
    public Long getId() { return id; }
    public Long getWorkerId() { return workerId; }
    public String getUnit() { return unit; }
    public String getIssue() { return issue; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setIssue(String issue) { this.issue = issue; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }
}