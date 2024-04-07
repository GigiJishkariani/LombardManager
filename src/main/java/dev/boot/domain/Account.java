package dev.boot.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long itemId;

    @PositiveOrZero(message = "Amount paid by user must be a non-negative value")
    private double amountPaidByUser;

    @PositiveOrZero(message = "Total to be paid must be a non-negative value")
    private double totalToBePaid;
}
