package com.valentine.grind.dto;

import java.math.BigDecimal;

public record CoffeeBeanResponseDto(
    Long id,
    String name,
    String origin,
    String roastLevel,
    BigDecimal pricePerKg,
    Integer stockQuantity,
    RoasterResponseDto roasterResponseDto
) {
}
