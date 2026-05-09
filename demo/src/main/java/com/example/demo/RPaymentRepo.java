package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RPaymentRepo extends JpaRepository<RPaymentModel, Integer>  {
	 // SELECT * FROM payments WHERE resident_id = ?
    List<RPaymentModel> findByResidentId(int residentId);

    // الفواتير اللي لسه مش مدفوعة (Pending)
    // SELECT * FROM payments WHERE resident_id = ? AND status = ?
    List<RPaymentModel> findByResidentIdAndStatus(int residentId, String status);

    // كل الفواتير المدفوعة
    // SELECT * FROM payments WHERE resident_id = ? AND status = 'Paid'
    List<RPaymentModel> findByResidentIdAndPayMethod(int residentId, String payMethod);
}