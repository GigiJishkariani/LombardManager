package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Tech extends Item {

    @Enumerated(EnumType.STRING)
    private TechType techType;

    @NotBlank(message = "Manufacturer is required for tech")
    private String manufacturer;

    @NotBlank(message = "Description is required for tech")
    private String description; //დაზიანების აღწერა
    @NotNull(message = "Usage allowed status is required for tech")
    private boolean usageAllowed; //ტარების უფლება
}
