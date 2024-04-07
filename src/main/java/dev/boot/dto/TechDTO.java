package dev.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.boot.domain.TechType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Getter
@Setter
public class TechDTO extends ItemDTO{
    @JsonIgnore
    private long id;
    @JsonIgnore
    private BranchDTO branch;
    @NotBlank(message = "Person name is required for tech")
    private String personName;
    @PastOrPresent
    private LocalDate dateReceived;
    private double amountReceived;
    private double monthlyPayment;
    @NotNull(message = "Tech type is required")
    private TechType techType;
    @NotBlank(message = "Manufacturer is required for tech")
    private String manufacturer;
    @NotBlank(message = "Description is required for tech")
    private String description;
    private boolean usageAllowed;
}
