package com.valentine.daily_grind.controller;

import com.valentine.daily_grind.dto.ApiResponse;
import com.valentine.daily_grind.dto.RoasterRequestDto;
import com.valentine.daily_grind.dto.RoasterResponseDto;
import com.valentine.daily_grind.service.RoasterService;
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
@RequestMapping("/api/v1/roasters")
public class RoasterController {

  private final RoasterService roasterService;

  @Autowired
  public RoasterController(RoasterService roasterService){
    this.roasterService = roasterService;
  }

  // CRUD
  // CREATE
  @PostMapping
  public ResponseEntity<ApiResponse<RoasterResponseDto>> create(
      @Valid @RequestBody RoasterRequestDto roasterRequestDto){
    RoasterResponseDto dtoResponse = roasterService.create(roasterRequestDto);
    ApiResponse<RoasterResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Created roaster successfully.",
        HttpStatus.CREATED.value()
    );
    return ResponseEntity
        .created(URI.create("/api/v1/roasters/" + dtoResponse.id()))
        .body(apiResponse);
  }

  // READ
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<RoasterResponseDto>> getById(
      @PathVariable Long id
  ){
    RoasterResponseDto dtoResponse = roasterService.getById(id);
    ApiResponse<RoasterResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Get roaster successfully",
        HttpStatus.OK.value()
    );
    return ResponseEntity.ok(apiResponse);
  }

  @GetMapping
  public ResponseEntity<Page<RoasterResponseDto>> getAll(
      Pageable pageable,
      @RequestParam(required = false) String name
  ){
    return ResponseEntity.ok(
        roasterService.getAll(pageable, name));
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<RoasterResponseDto>> update(
      @PathVariable Long id,
      @Valid @RequestBody RoasterRequestDto roasterRequestDto
  ){
    RoasterResponseDto dtoResponse = roasterService.update(id, roasterRequestDto);
    ApiResponse<RoasterResponseDto> apiResponse = ApiResponse.success(
        dtoResponse,
        "Updated roaster successfully",
        HttpStatus.OK.value()
    );
    return ResponseEntity.ok(apiResponse);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(
      @PathVariable Long id
  ){
    roasterService.delete(id);
    return ResponseEntity
        .noContent()
        .build();
  }


}
