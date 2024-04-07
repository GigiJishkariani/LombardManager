package dev.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
public class JewelryDTO extends ItemDTO{
    @JsonIgnore
    private long id;
    @JsonIgnore
    private BranchDTO branch;
    @NotBlank(message = "Person name is required for jewelry")
    private String personName;
    @PastOrPresent
    private LocalDate dateReceived;
    private double amountReceived;
    private double monthlyPayment;
    @NotBlank(message = "Description is required for jewelry")
    private String description;
    @NotBlank(message = "Type is required for jewelry")
    private String type;
}
