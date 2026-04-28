package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<PaymentModel> getPaymentsByResident(int resId) {
        List<PaymentModel> payments = paymentRepository.findByResId(resId);
        if (payments.isEmpty()) {
            System.out.println("No payments found for this resident");
        }
        return payments;
    }

    public List<PaymentModel> getOverduePayments() {
        List<PaymentModel> overdue = paymentRepository.findByStatus("Overdue");
        if (overdue.isEmpty()) {
            System.out.println("No overdue payments found");
        }
        return overdue;
    }

    public PaymentModel markAsPaid(int paymentId) {
        Optional<PaymentModel> result = paymentRepository.findById(paymentId);

        if (result.isEmpty()) {
            System.out.println("Payment not found");
            return null;
        }

        PaymentModel p = result.get();

        if ("Paid".equalsIgnoreCase(p.getStatus())) {
            System.out.println("Payment is already paid");
            return null;
        }

        p.setStatus("Paid");
        return paymentRepository.save(p);
    }

    public PaymentModel checkAndUpdateOverdue(int paymentId) {
        Optional<PaymentModel> result = paymentRepository.findById(paymentId);

        if (result.isEmpty()) {
            System.out.println("Payment not found");
            return null;
        }

        PaymentModel p = result.get();

        if (p.getStatus().equalsIgnoreCase("Paid")) {
            System.out.println("Payment is already paid, no need to check");
            return p;
        }

        if (p.getDueDate() == null) {
            System.out.println("No due date set for this payment");
            return p;
        }

        if (p.getDueDate().isBefore(LocalDate.now())) {
            p.setStatus("Overdue");
            return paymentRepository.save(p);
        }

        return p;
    }
    public PaymentModel addPayment(PaymentModel payment) {
        return paymentRepository.save(payment);
    }
}