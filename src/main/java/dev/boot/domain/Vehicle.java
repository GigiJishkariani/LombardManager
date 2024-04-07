package dev.boot.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
public class Vehicle extends Item {

    @NotBlank(message = "Model is required for vehicle")
    private String model;

    @Positive(message = "Release year must be a positive value")
    private int releaseYear;

    @Positive(message = "Odometer value must be a positive value")
    private long odometer;

    @NotNull(message = "Distance unit is required for vehicle")
    private DistanceUnit distanceUnit;
}

