package payment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentModel, Integer> {

    List<PaymentModel> findByResId(int resId);

    List<PaymentModel> findByStatus(String status);
}