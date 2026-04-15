package com.valentine.grind.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoasterRequestDto(
    @NotBlank String name,
    @NotBlank String location,
    @Min(value = 1800, message = "Year must be after 1800")
    @Max(value = 2026, message = "Year cannot be in the future")
    @NotNull Integer yearEstablished
) { }
