package cookie.payment.service;

import cookie.payment.mapper.PaymentMapper;
import cookie.payment.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentMapper paymentMapper;

    private PaymentServiceImpl(PaymentMapper paymentMapper){ this.paymentMapper = paymentMapper;}

    private static final AtomicInteger PAYMENT_ID_HOLDER = new AtomicInteger();

     @Override
    public List<Payment> create(@RequestBody Map<String, String> paymentCreate) {
        Payment payment = new Payment();
        payment.setName(paymentCreate.get("name"));
        payment.setId(PAYMENT_ID_HOLDER.incrementAndGet());
        payment.setCash(Integer.parseInt(paymentCreate.get("cash")));
        paymentMapper.paymentCreate(payment);
        return paymentMapper.readAll();
    }

    @Override
    public List<Payment> readAll() {
        return paymentMapper.readAll();
    }

    @Override
    public Payment read(int id) {
        return null;
    }

    @Override
    public boolean update(@RequestBody Map<String, String> paymentUpdate) {
        Payment payment = new Payment();
        payment.setName(paymentUpdate.get("name"));
        payment.setId(Integer.parseInt(paymentUpdate.get("id")));
        payment.setCash(Integer.parseInt(paymentUpdate.get("cash")));
        paymentMapper.updatePayments(payment);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Payment payment = new Payment();
        payment.setId(id);
        paymentMapper.paymentDelete(payment.getId());
        return true;
    }
}
