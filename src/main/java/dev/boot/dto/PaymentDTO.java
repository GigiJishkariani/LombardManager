package dev.boot.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    @Positive(message = "Item ID must be a positive value")
    private Long itemId;

    @Positive(message = "User ID must be a positive value")
    private Long userId;

    private double amountPaid;

    @NotNull(message = "Payment date is required")
    @PastOrPresent
    private LocalDate paymentDate;


}


