package cookie.payment.controller;

import cookie.payment.mapper.PaymentMapper;
import cookie.payment.model.Payments;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/payment")
public class PaymentController {

    private PaymentMapper paymentMapper;

    public PaymentController(PaymentMapper paymentMapper){ this.paymentMapper = paymentMapper;}

    @GetMapping("")
    public List<Payments> getAll() {return  paymentMapper.findAll();}

    @PostMapping("/update")
    private List<Payments> update(@RequestBody Map<String, String> paymentUpdate){
        Payments payment = new Payments();
        payment.setName(paymentUpdate.get("name"));
        payment.setId(Integer.parseInt(paymentUpdate.get("id")));
        payment.setCash(Integer.parseInt(paymentUpdate.get("cash")));
        paymentMapper.updatePayments(payment);
        return paymentMapper.findAll();
    }
    @PostMapping("/create")
    private List<Payments> create(@RequestBody Map<String, String> paymentCreate){
        Payments payment = new Payments();
        payment.setName(paymentCreate.get("name"));
        payment.setId(Integer.parseInt(paymentCreate.get("id")));
        payment.setCash(Integer.parseInt(paymentCreate.get("cash")));
        paymentMapper.paymentCreate(payment);
        return paymentMapper.findAll();
    }

    @PostMapping("/delete")
    private List<Payments> delete(@RequestBody Map<String, String> paymentDelete){
        Payments payment = new Payments();
        payment.setName(paymentDelete.get("name"));
        payment.setId(Integer.parseInt(paymentDelete.get("id")));
        payment.setCash(Integer.parseInt(paymentDelete.get("cash")));
        paymentMapper.paymentDelete(payment);
        return paymentMapper.findAll();
    }
}
