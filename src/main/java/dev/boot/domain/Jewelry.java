package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class Jewelry extends Item {

    @NotBlank(message = "Description is required for jewelry")
    private String description;
    @NotBlank(message = "Type is required for jewelry")
    private String type; //გამოყენებული ლითონი
}
