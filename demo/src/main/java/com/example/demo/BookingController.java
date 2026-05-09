package com.example.demo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    // ====================================================
    // عرض صفحة الحجوزات — GET /booking
    // ====================================================
    @GetMapping("/booking")
    public String showBookings(HttpSession session, Model model) {

        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";

        // جيب كل الحجوزات من الداتابيز وابعتها للـ HTML
        model.addAttribute("bookings", bookingService.getMyBookings(residentId));

        // object فاضي للـ form بتاع الحجز الجديد
        model.addAttribute("newBooking", new BookingModel());

        return "booking";
    }

    // ====================================================
    // إضافة حجز جديد من الـ form — POST /booking/add
    // ====================================================
    @PostMapping("/booking/add")
    public String addBooking(@ModelAttribute("newBooking") BookingModel booking,
                             HttpSession session,
                             Model model) {

        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";
        String residentName = (String) session.getAttribute("username");

        booking.setResidentId(residentId);
        booking.setResidentName(residentName);

        String result = bookingService.addBooking(booking);
        model.addAttribute("message", result);

        return "redirect:/booking";
    }

    // ====================================================
    // إلغاء حجز — POST /booking/cancel/{bookingId}
    // ====================================================
    @PostMapping("/booking/cancel/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId,
                                HttpSession session) {

        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";
        bookingService.cancelBooking(bookingId, residentId);

        return "redirect:/booking";
    }

    // ====================================================
    // فلترة الحجوزات بالحالة — GET /booking/filter/{status}
    // ====================================================
    @GetMapping("/booking/filter/{status}")
    public String filterByStatus(@PathVariable String status,
                                 HttpSession session,
                                 Model model) {

        Integer residentId = (Integer) session.getAttribute("residentId");
        if (residentId == null) return "redirect:/login";
        model.addAttribute("bookings",
                bookingService.getMyBookingsByStatus(residentId, status));
        model.addAttribute("newBooking", new BookingModel());

        return "booking";
    }
}