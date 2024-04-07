package dev.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
public class ItemDTO {

    @JsonIgnore
    private long id;
    @JsonIgnore
    @NotNull(message = "Branch is required")
    private BranchDTO branch;
    private long personId;
    @NotNull(message = "Date received is required")
    @PastOrPresent
    private LocalDate dateReceived;
    private double amountReceived;
    private double monthlyPayment;
    @JsonIgnore
    private boolean takenFromUser = false;
    @JsonIgnore
    private boolean userTookOut = false; //defaultad trues agiqvamda
    @JsonIgnore
    @PastOrPresent
    private LocalDate itemTakingDate;
}
