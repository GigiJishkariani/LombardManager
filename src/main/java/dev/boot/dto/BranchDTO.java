package dev.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class BranchDTO {
    private long id;

    @NotBlank(message = "Branch name is required")
    private String name;

    @NotNull(message = "Items list is required")
    private List<ItemDTO> items;



    @Schema(hidden = true)
    public void setId(long id) {
        this.id = id;
    }


    @Schema(hidden = true)
    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
