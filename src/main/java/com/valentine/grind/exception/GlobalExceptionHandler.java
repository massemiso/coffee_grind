package com.valentine.grind.exception;

import com.valentine.grind.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CoffeeBeanNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleCoffeeBeanNotFoundException(CoffeeBeanNotFoundException e) {
    ApiResponse<Void> response = ApiResponse.error(
        e.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );
    return new ResponseEntity<>(response,  HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RoasterNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleRoasterNotFoundException(RoasterNotFoundException e) {
    ApiResponse<Void> response = ApiResponse.error(
        e.getMessage(),
        HttpStatus.NOT_FOUND.value()
    );
    return new ResponseEntity<>(response,  HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ApiResponse<Void>> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException ex,
      HttpServletRequest request){
    ApiResponse<Void> response = ApiResponse.error(
        ex.getMessage(),
        HttpStatus.NOT_ACCEPTABLE.value()
    );
    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
      MethodArgumentNotValidException ex) {

    // Collect all validation errors (e.g., "email": "must be a valid email")
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );

    // Here we use the 'data' field to send the specific validation details
    ApiResponse<Map<String, String>> response = new ApiResponse<>(
        errors,
        "Validation failed",
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.value()
    );

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(
      IllegalArgumentException ex,
      HttpServletRequest request
  ){
    ApiResponse<Void> response = ApiResponse.error(
        ex.getMessage(),
        HttpStatus.BAD_REQUEST.value()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

}
