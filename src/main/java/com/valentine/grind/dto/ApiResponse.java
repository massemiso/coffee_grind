package com.valentine.grind.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(
    T data,
    String message,
    LocalDateTime timestamp,
    int status
) {
  public static <T> ApiResponse<T> success(T data, String message, int status){
    return new ApiResponse<>(data, message, LocalDateTime.now(), status);
  }

  public static ApiResponse<Void> error(String message, int status) {
    return new ApiResponse<>(null, message, LocalDateTime.now(), status);
  }
}
