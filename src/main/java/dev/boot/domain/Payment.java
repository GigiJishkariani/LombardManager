package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Item ID must be a positive value")
    private Long itemId;

    @Positive(message = "User ID must be a positive value")
    private Long userId;

    @PositiveOrZero(message = "Amount paid must be a non-negative value")
    private double amountPaid;

    @NotNull(message = "Payment date is required")
    @PastOrPresent
    private LocalDate paymentDate;
}

