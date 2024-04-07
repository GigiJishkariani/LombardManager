package dev.boot.controller;


import dev.boot.dto.PaymentDTO;
import dev.boot.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping
    @Operation(summary = "Makes new payment")
    public ResponseEntity<PaymentDTO> save(@RequestBody PaymentDTO paymentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.recordPayment(paymentDTO));
    }
}
