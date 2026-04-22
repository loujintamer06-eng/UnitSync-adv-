package payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentModel> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<PaymentModel> getPaymentsByResident(int resId) {
        return paymentRepository.findByResId(resId);
    }

    public List<PaymentModel> getOverduePayments() {
        return paymentRepository.findByStatus("Overdue");
    }

    public PaymentModel markAsPaid(int paymentId) {
        PaymentModel p = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if ("Paid".equalsIgnoreCase(p.getStatus())) {
            throw new IllegalStateException("Payment is already paid");
        }

        p.setStatus("Paid");
        return paymentRepository.save(p);
    }

    public PaymentModel checkAndUpdateOverdue(int paymentId) {
        PaymentModel p = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (!"Paid".equalsIgnoreCase(p.getStatus())
                && p.getDueDate() != null
                && p.getDueDate().isBefore(LocalDate.now())) {

            p.setStatus("Overdue");
            return paymentRepository.save(p);
        }

        return p;
    }
}