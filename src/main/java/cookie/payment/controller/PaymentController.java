package cookie.payment.controller;

import cookie.payment.mapper.PaymentMapper;
import cookie.payment.model.Payment;
import cookie.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Payment>> readAll() {
        final List<Payment> payments = paymentService.readAll();

        return payments != null && !payments.isEmpty()
                ? new ResponseEntity<>(payments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> payment(@RequestBody Map<String,String> paymentRead){
        final boolean paymentRead = paymentService.readOne(paymentRead);

    }

    @PostMapping("")
    private ResponseEntity<?> update(@RequestBody Map<String, String> paymentUpdate){
        final boolean updated = paymentService.update(paymentUpdate);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("")
    private ResponseEntity<?> create(@RequestBody Map<String, String> paymentCreate){
        paymentService.create(paymentCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean deleted = paymentService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}