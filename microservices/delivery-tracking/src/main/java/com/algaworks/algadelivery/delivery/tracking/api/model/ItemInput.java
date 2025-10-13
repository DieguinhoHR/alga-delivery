package com.algaworks.algadelivery.delivery.tracking.api.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInput {

    @NotBlank
    private String name;

    @Min(1)
    private Integer quantity;
}
