package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepo bookingRepository;

    @Autowired
    RPaymentRepo paymentRepository;

    // ====================================================
    // 1. جيب كل حجوزات الـ resident من الداتابيز
    // ====================================================
    public List<BookingModel> getMyBookings(int residentId) {
        return bookingRepository.findByResidentId(residentId);
    }

    // ====================================================
    // 2. جيب حجوزاته بحالة معينة
    // ====================================================
    public List<BookingModel> getMyBookingsByStatus(int residentId, String status) {
        return bookingRepository.findByResidentIdAndStatus(residentId, status);
    }

    // ====================================================
    // 3. إضافة حجز جديد وحفظه في الداتابيز
    // ====================================================
    public String addBooking(BookingModel booking) {

        if (booking.getResidentName() == null || booking.getResidentName().isEmpty()) {
            return "Full name is required";
        }
        if (booking.getAmenityName() == null || booking.getAmenityName().isEmpty()) {
            return "Amenity type is required";
        }
        if (booking.getBookingDate() == null || booking.getBookingDate().isEmpty()) {
            return "Booking date is required";
        }
        if (booking.getBookingTime() == null || booking.getBookingTime().isEmpty()) {
            return "Booking time is required";
        }
        if (booking.getAmount() <= 0) {
            return "Amount must be greater than zero";
        }

        // التأكد مفيش تعارض على نفس الـ amenity في نفس اليوم والوقت
        List<BookingModel> conflicts = bookingRepository
            .findByAmenityNameAndBookingDateAndBookingTime(
                booking.getAmenityName(),
                booking.getBookingDate(),
                booking.getBookingTime()
            );

        if (!conflicts.isEmpty()) {
            return "This time slot is already booked for " + booking.getAmenityName();
        }

        // تحديد الـ status وتاريخ الإنشاء تلقائياً
        booking.setStatus("Pending");
        booking.setCreatedAt(
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        bookingRepository.save(booking);

        // Auto-create a Pending payment for this booking
        RPaymentModel payment = new RPaymentModel();
        payment.setResidentId(booking.getResidentId());
        payment.setAmenity(booking.getAmenityName());
        payment.setAmount(booking.getAmount());
        payment.setDueDate(booking.getBookingDate());
        payment.setStatus("Pending");
        System.out.println("DEBUG: saving payment for residentId=" + payment.getResidentId() 
        + " amenity=" + payment.getAmenity() 
        + " amount=" + payment.getAmount());
        paymentRepository.save(payment);

        return "Booking confirmed successfully!";
    }

    // ====================================================
    // 4. إلغاء حجز — بيغير الـ status بس مش بيمسحه
    // ====================================================
    public String cancelBooking(int bookingId, int residentId) {
        BookingModel booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getResidentId() != residentId) {
            return "Unauthorized: This booking does not belong to you";
        }
        if (booking.getStatus().equals("Cancelled")) {
            return "Booking is already cancelled";
        }

        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
        return "Booking cancelled successfully";
    }

    // ====================================================
    // 5. تعديل موعد الحجز
    // ====================================================
    public String rescheduleBooking(int bookingId, int residentId,
                                    String newDate, String newTime) {
        BookingModel booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getResidentId() != residentId) {
            return "Unauthorized";
        }
        if (booking.getStatus().equals("Cancelled")) {
            return "Cannot reschedule a cancelled booking";
        }

        booking.setBookingDate(newDate);
        booking.setBookingTime(newTime);
        booking.setStatus("Pending"); // بترجع Pending بعد التعديل
        bookingRepository.save(booking);
        return "Booking rescheduled successfully";
    }

    // ====================================================
    // 6. جيب حجز واحد بالـ ID
    // ====================================================
    public BookingModel getBookingById(int bookingId) {
        return bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // ====================================================
    // 7. حذف حجز نهائياً من الداتابيز
    // ====================================================
    public String deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
        return "Booking deleted successfully";
    }
}