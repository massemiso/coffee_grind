package com.valentine.grind.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CoffeeBeanRequestDto(
    @NotBlank String name,
    @NotBlank String origin,
    @NotNull(message = "Roast level is required") String roastLevel,
    @NotNull @Min(0) BigDecimal pricePerKg,
    @NotNull @Min(0) Integer stockQuantity,
    @NotNull Long roasterId
) {
}
