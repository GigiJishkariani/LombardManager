package dev.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.boot.domain.DistanceUnit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
@Getter
@Setter
public class VehicleDTO extends ItemDTO{

    @JsonIgnore
    private long id;

    @JsonIgnore
    private BranchDTO branch;

    @NotBlank(message = "Person name is required for vehicle")
    private String personName;

    @PastOrPresent
    private LocalDate dateReceived;

    private double amountReceived;

    private double monthlyPayment;

    private String model;

    @Positive(message = "Release year must be a positive value")
    private int releaseYear;

    @Positive(message = "Odometer value must be a positive value")
    private long odometer;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Distance unit is required for vehicle")
    private DistanceUnit distanceUnit = DistanceUnit.KILOMETER;
}
