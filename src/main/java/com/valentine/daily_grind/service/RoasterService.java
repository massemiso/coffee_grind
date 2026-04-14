package com.valentine.daily_grind.service;

import com.valentine.daily_grind.dto.RoasterMapper;
import com.valentine.daily_grind.dto.RoasterRequestDto;
import com.valentine.daily_grind.dto.RoasterResponseDto;
import com.valentine.daily_grind.exception.RoasterNotFoundException;
import com.valentine.daily_grind.model.Roaster;
import com.valentine.daily_grind.repository.RoasterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoasterService {
  private final RoasterRepository roasterRepository;
  private final RoasterMapper roasterMapper;

  @Autowired
  public RoasterService(RoasterRepository roasterRepository, RoasterMapper roasterMapper) {
    this.roasterRepository = roasterRepository;
    this.roasterMapper = roasterMapper;
  }

  // CRUD
  // CREATE
  @Transactional
  public RoasterResponseDto create(RoasterRequestDto roasterRequestDto) {
    Roaster roaster = roasterMapper.toEntity(roasterRequestDto);
    roasterRepository.save(roaster);
    return roasterMapper.toDto(roaster);
  }

  // READ
  public RoasterResponseDto getById(Long id) {
    Roaster roaster = roasterRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new RoasterNotFoundException(id));
    return roasterMapper.toDto(roaster);
  }

  public Page<RoasterResponseDto> getAll(Pageable pageable, String name) {
    Page<RoasterResponseDto> page;
    if (name == null || name.isBlank()){
      page = roasterRepository
          .findByDeletedAtIsNull(pageable)
          .map(roasterMapper::toDto);
    }
    else{
      page = roasterRepository
          .findByDeletedAtIsNullAndNameContainingIgnoreCase(name,pageable)
          .map(roasterMapper::toDto);
    }
    return page;
  }


  // UPDATE
  @Transactional
  public RoasterResponseDto update(Long id, RoasterRequestDto roasterRequestDto) {
    Roaster roaster = roasterRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new RoasterNotFoundException(id));

    roaster.setName(roasterRequestDto.name());
    roaster.setLocation(roasterRequestDto.location());
    roaster.setYearEstablished(roasterRequestDto.yearEstablished());
    roasterRepository.save(roaster);

    return roasterMapper.toDto(roaster);
  }

  // DELETE
  @Transactional
  public void delete(Long id) {
    Roaster roaster = roasterRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() ->  new RoasterNotFoundException(id));
    roaster.delete();
    roasterRepository.save(roaster);
  }
}
