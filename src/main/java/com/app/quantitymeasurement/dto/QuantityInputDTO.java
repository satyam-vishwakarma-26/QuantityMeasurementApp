package com.app.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityInputDTO {

    // First quantity involved in the operation
    @NotNull(message = "thisQuantityDTO cannot be null")
    @Valid
    private QuantityDTO thisQuantityDTO;

    // Second quantity involved in the operation
    @NotNull(message = "thatQuantityDTO cannot be null")
    @Valid
    private QuantityDTO thatQuantityDTO;

    // Optional target unit for add-with-target-unit and subtract-with-target-unit
    @Valid
    private QuantityDTO targetQuantityDTO;
}