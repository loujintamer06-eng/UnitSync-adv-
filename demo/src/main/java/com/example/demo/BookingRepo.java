package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<BookingModel, Integer> {
	    // كل حجوزات resident معين
	    List<BookingModel> findByResidentId(int residentId);

	    // حجوزات بحالة معينة
	    List<BookingModel> findByResidentIdAndStatus(int residentId, String status);

	    // حجوزات يوم معين
	    List<BookingModel> findByBookingDate(String bookingDate);

	    // تأكيد مفيش تعارض على نفس الـ amenity في نفس اليوم والوقت
	    List<BookingModel> findByAmenityNameAndBookingDateAndBookingTime(
	        String amenityName, String bookingDate, String bookingTime);
	    
	    List<BookingModel> findByResidentIdAndAmenityNameAndStatus(
	    	    int residentId, String amenityName, String status
	    	);
	}