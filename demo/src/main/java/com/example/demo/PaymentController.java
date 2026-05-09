package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping
    public List<PaymentModel> getAll() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/overdue")
    public List<PaymentModel> getOverdue() {
        return paymentService.getOverduePayments();
    }

    @PatchMapping("/{id}/pay")
    public PaymentModel markAsPaid(@PathVariable int id) {
        return paymentService.markAsPaid(id);
    }

    @PatchMapping("/{id}/checkOverdue")
    public PaymentModel checkOverdue(@PathVariable int id) {
        return paymentService.checkAndUpdateOverdue(id);
    }
    @Autowired
    PaymentRepository paymentRepository;
    

    @PutMapping("/update/{id}/{status}") 
    @ResponseBody
    public String updatePaymentStatus(@PathVariable int id, @PathVariable String status) {
        PaymentModel p = paymentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Payment not found"));
        p.setStatus(status);
        paymentRepository.save(p);
        return "Updated";
    }
    @PostMapping("/add")
    public String addPayment(@RequestBody PaymentModel payment) {
        paymentService.addPayment(payment);
        return "Added";
    }
   
}
