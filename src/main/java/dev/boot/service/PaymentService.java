package dev.boot.service;

import dev.boot.domain.Payment;
import dev.boot.dto.PaymentDTO;
import dev.boot.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;


    public PaymentDTO recordPayment(PaymentDTO paymentDTO){
        Payment payment = paymentRepository.save(modelMapper.map(paymentDTO, Payment.class));

        accountService.update(paymentDTO.getItemId(), paymentDTO.getAmountPaid());
        return modelMapper.map(payment, PaymentDTO.class);
    }

}
