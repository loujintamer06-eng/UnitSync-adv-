package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RPaymentService {

    @Autowired
    RPaymentRepo paymentRepository;

    @Autowired
    BookingRepo bookingRepository;

    // ====================================================
    // 1. جيب كل الفواتير الـ Pending بتاعت الـ resident
    // ====================================================
    public List<RPaymentModel> getPendingPayments(int residentId) {
        return paymentRepository.findByResidentIdAndStatus(residentId, "Pending");
    }
    
    // ====================================================
    // 4. دفع فاتورة معينة وتحديث الداتابيز
    // ====================================================
    public String confirmPayment(int paymentId, int residentId,
                                  String firstName, String lastName,
                                  String payMethod, String cardNumber) {

        if (firstName == null || firstName.isEmpty()) {
            return "First name is required";
        }
        if (lastName == null || lastName.isEmpty()) {
            return "Last name is required";
        }
        if (payMethod.equals("card")) {
            if (cardNumber == null || cardNumber.length() < 16) {
                return "Card number must be 16 digits";
            }
        }

        RPaymentModel payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getResidentId() != residentId) {
            return "Unauthorized: This payment does not belong to you";
        }
        if (payment.getStatus().equals("Paid")) {
            return "This payment is already paid";
        }

        payment.setStatus("Paid");
        payment.setFirstName(firstName);
        payment.setLastName(lastName);
        payment.setPayMethod(payMethod);
        payment.setCardNumber(cardNumber);
        payment.setPaidAt(
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        paymentRepository.save(payment);

        // Delete the matching booking so it disappears from the booking page
        
        List<BookingModel> bookings = bookingRepository
            .findByResidentIdAndAmenityNameAndStatus(
                residentId, payment.getAmenity(), "Pending"
            );
        if (!bookings.isEmpty()) {
            bookingRepository.delete(bookings.get(0));
        }

        return "Paid successfully";
    }

    // ====================================================
    // 5. احسب إجمالي الديون اللي لسه مش مدفوعة
    // ====================================================
    public double getTotalPending(int residentId) {
        List<RPaymentModel> pending = getPendingPayments(residentId);
        double total = 0;
        for (RPaymentModel p : pending) {
            total += p.getAmount();
        }
        return total;
    }
}