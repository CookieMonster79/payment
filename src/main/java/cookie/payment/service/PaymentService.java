package cookie.payment.service;

import cookie.payment.model.Payment;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    List<Payment> create(@RequestBody Map<String, String> paymentCreate);

    List<Payment> readAll();

    Payment readOne(int id);

    boolean update (@RequestBody Map<String, String> paymentUpdate);

    boolean delete(int id);
}
