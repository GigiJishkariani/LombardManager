package dev.boot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@SequenceGenerator(name = "ITEM_ID_GEN", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
public class Item {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GEN")
    private long id;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @NotNull(message = "Branch is required")
    private Branch branch;

    private long personId;

    @NotNull(message = "Date received is required")
    @PastOrPresent
    private LocalDate dateReceived;

    @PositiveOrZero(message = "Amount received must be a non-negative value")
    private double amountReceived;

    @PositiveOrZero(message = "Monthly payment must be a non-negative value")
    private double monthlyPayment;
    @JsonIgnore
    private boolean takenFromUser;
    @JsonIgnore
    private boolean userTookOut;
    @JsonIgnore
    @PastOrPresent
    private LocalDate itemTakingDate; //rodis gaitana an chamoertva nivti
}
