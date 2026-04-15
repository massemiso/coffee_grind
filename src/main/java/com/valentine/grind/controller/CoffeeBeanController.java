package com.valentine.grind.controller;

import com.valentine.grind.dto.ApiResponse;
import com.valentine.grind.dto.CoffeeBeanRequestDto;
import com.valentine.grind.dto.CoffeeBeanResponseDto;
import com.valentine.grind.model.RoastLevel;
import com.valentine.grind.service.CoffeeBeanService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beans")
public class CoffeeBeanController {

  private final CoffeeBeanService coffeeBeanService;

  @Autowired
  public CoffeeBeanController(CoffeeBeanService coffeeBeanService){
    this.coffeeBeanService = coffeeBeanService;
  }

  /* CRUD */

  // CREATE
  @PostMapping
  public ResponseEntity<ApiResponse<CoffeeBeanResponseDto>> create(
      @Valid @RequestBody CoffeeBeanRequestDto coffeeBeanRequestDto
  ){
    CoffeeBeanResponseDto dtoResponse = coffeeBeanService.create(coffeeBeanRequestDto);
    ApiResponse<CoffeeBeanResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Created coffee bean successfully",
        HttpStatus.CREATED.value()
    );

    return ResponseEntity
        .created(URI.create("/api/v1/beans/" + dtoResponse.id()))
        .body(apiResponse);
  }

  // READ
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<CoffeeBeanResponseDto>> getById(
      @PathVariable Long id
  ){
    CoffeeBeanResponseDto dtoResponse = coffeeBeanService.getById(id);
    ApiResponse<CoffeeBeanResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Get coffee bean successfully",
        HttpStatus.OK.value()
    );
    return ResponseEntity.ok(apiResponse);
  }

  @GetMapping
  public ResponseEntity<Page<CoffeeBeanResponseDto>> getAll(
      Pageable pageable,
      @RequestParam(required = false) RoastLevel roastLevel
  ){
    return ResponseEntity.ok(
            coffeeBeanService.getAll(pageable, roastLevel));
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<CoffeeBeanResponseDto>> update(
      @PathVariable Long id,
      @Valid @RequestBody CoffeeBeanRequestDto coffeeBeanRequestDto
  ){
    CoffeeBeanResponseDto dtoResponse = coffeeBeanService.update(id, coffeeBeanRequestDto);
    ApiResponse<CoffeeBeanResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Updated coffee bean successfully",
        HttpStatus.OK.value()
    );
    return ResponseEntity.ok(apiResponse);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(
      @PathVariable Long id
  ){
    coffeeBeanService.delete(id);
    return ResponseEntity
        .noContent()
        .build();
  }


}
