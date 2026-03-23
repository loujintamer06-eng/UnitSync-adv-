package adv;

import java.time.LocalDate;

public class Payment implements Payable {

    private int paymentId;
    private int resid;
    private double amount;
    private String status;
    private LocalDate dueDate;
    private LocalDate paidDate;

    public Payment(int resid, double amount, LocalDate dueDate) {
        this.resid = resid;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = "Unpaid";
    }
    
    @Override
    public double calculateAmount() {
        if ("Overdue".equals(status)) {
            return amount * 1.10;
        }
        return amount;
    }

    @Override
    public boolean isPaid() {
        return "Paid".equals(status);
    }

    @Override
    public String getPaymentStatus() {
        if (isPaid())
            return "Payment completed on " + paidDate;
        if ("Overdue".equals(status))
            return "Overdue since " + dueDate;
        return "Due on " + dueDate;
    }
    
    public void markAsPaid() {
        this.status = "Paid";
        this.paidDate = LocalDate.now();
    }

    public void checkIfOverdue() {
        if (!isPaid() && LocalDate.now().isAfter(dueDate)) {
            this.status = "Overdue";
        }
    }
    
    public boolean isValidAmount() {
        return amount > 0;
    }

    public boolean isValidStatus() {
        return status != null && (status.equals("Paid") || 
               status.equals("Unpaid") || status.equals("Overdue"));
    }

    public boolean isValidDueDate() {
        return dueDate != null;
    }

    public boolean isValid() {
        return isValidAmount() && isValidStatus() && isValidDueDate();
    }

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}
}