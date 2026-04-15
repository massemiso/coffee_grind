package com.valentine.grind.dto;

public record RoasterResponseDto(
    Long id,
    String name,
    String location,
    Integer yearEstablished,
    Boolean isActive
) {
}
