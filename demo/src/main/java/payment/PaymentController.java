package payment;

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

    @GetMapping("/resident/{resId}")
    public List<PaymentModel> getByResident(@PathVariable int resId) {
        return paymentService.getPaymentsByResident(resId);
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
}
