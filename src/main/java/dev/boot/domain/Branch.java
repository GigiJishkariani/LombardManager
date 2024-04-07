package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "BRANCH_ID_GEN", sequenceName = "pawnshop.branch_id_seq", allocationSize = 1)
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BRANCH_ID_GEN")
    private long id;

    @NotBlank(message = "Branch name is required")
    private String name;

    @OneToMany(mappedBy = "branch")
    private List<Item> items;
}

