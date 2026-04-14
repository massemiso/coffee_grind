package com.valentine.daily_grind.service;

import com.valentine.daily_grind.dto.CoffeeBeanMapper;
import com.valentine.daily_grind.dto.CoffeeBeanRequestDto;
import com.valentine.daily_grind.dto.CoffeeBeanResponseDto;
import com.valentine.daily_grind.dto.RoasterMapper;
import com.valentine.daily_grind.dto.RoasterResponseDto;
import com.valentine.daily_grind.exception.CoffeeBeanNotFoundException;
import com.valentine.daily_grind.exception.RoasterNotFoundException;
import com.valentine.daily_grind.model.CoffeeBean;
import com.valentine.daily_grind.model.RoastLevel;
import com.valentine.daily_grind.model.Roaster;
import com.valentine.daily_grind.repository.CoffeeBeanRepository;
import com.valentine.daily_grind.repository.RoasterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoffeeBeanService {

  private final CoffeeBeanRepository coffeeBeanRepository;
  private final RoasterRepository roasterRepository;
  private final CoffeeBeanMapper coffeeBeanMapper;
  private final RoasterMapper roasterMapper;

  @Autowired
  public CoffeeBeanService(
      CoffeeBeanRepository coffeeBeanRepository,
      RoasterRepository roasterRepository,
      CoffeeBeanMapper coffeeBeanMapper,
      RoasterMapper roasterMapper
  ) {
    this.coffeeBeanRepository = coffeeBeanRepository;
    this.roasterRepository = roasterRepository;
    this.coffeeBeanMapper = coffeeBeanMapper;
    this.roasterMapper = roasterMapper;
  }

  // CREATE
  @Transactional
  public CoffeeBeanResponseDto create(CoffeeBeanRequestDto coffeeBeanRequestDto) {
    Roaster roaster = roasterRepository
        .findByIdAndDeletedAtIsNull(coffeeBeanRequestDto.roasterId())
        .orElseThrow(() -> new RoasterNotFoundException(coffeeBeanRequestDto.roasterId()));
    CoffeeBean coffeeBean = coffeeBeanMapper.toEntity(coffeeBeanRequestDto, roaster);
    coffeeBeanRepository.save(coffeeBean);
    return coffeeBeanMapper.toDto(coffeeBean, roasterMapper.toDto(roaster));
  }

  // READ
  public CoffeeBeanResponseDto getById(Long id) {
    CoffeeBean coffeeBean = coffeeBeanRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new CoffeeBeanNotFoundException(id));
    RoasterResponseDto roasterResponseDto = roasterMapper.toDto(coffeeBean.getRoaster());
    return coffeeBeanMapper.toDto(coffeeBean, roasterResponseDto);
  }

  public Page<CoffeeBeanResponseDto> getAll(Pageable pageable, RoastLevel roastLevel) {
    Page<CoffeeBeanResponseDto> page;
    if (roastLevel == null){
      page = coffeeBeanRepository
          .findByDeletedAtIsNull(pageable)
          .map(coffeeBean -> coffeeBeanMapper.toDto
              (coffeeBean,
                  roasterMapper.toDto(coffeeBean.getRoaster())));
    }
    else{
      page = coffeeBeanRepository
          .findByDeletedAtIsNullAndRoastLevel(roastLevel, pageable)
          .map(coffeeBean -> coffeeBeanMapper.toDto
              (coffeeBean,
                  roasterMapper.toDto(coffeeBean.getRoaster())));
    }
    return page;
  }


  // UPDATE
  @Transactional
  public CoffeeBeanResponseDto update(Long id, CoffeeBeanRequestDto coffeeBeanRequestDto) {
    CoffeeBean coffeeBean = coffeeBeanRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() -> new CoffeeBeanNotFoundException(id));
    Roaster roaster = roasterRepository
        .findByIdAndDeletedAtIsNull(coffeeBeanRequestDto.roasterId())
        .orElseThrow(() -> new RoasterNotFoundException(coffeeBeanRequestDto.roasterId()));

    coffeeBean.setName(coffeeBeanRequestDto.name());
    coffeeBean.setOrigin(coffeeBeanRequestDto.origin());
    coffeeBean.setRoastLevel(
        RoastLevel.valueOf(coffeeBeanRequestDto.roastLevel().toUpperCase()));
    coffeeBean.setPricePerKg(coffeeBeanRequestDto.pricePerKg());
    coffeeBean.setStockQuantity(coffeeBeanRequestDto.stockQuantity());
    coffeeBean.setRoaster(roaster);
    coffeeBeanRepository.save(coffeeBean);

    return coffeeBeanMapper.toDto(coffeeBean, roasterMapper.toDto(roaster));
  }

  // DELETE
  @Transactional
  public void delete(Long id) {
    CoffeeBean coffeeBean = coffeeBeanRepository
        .findByIdAndDeletedAtIsNull(id)
        .orElseThrow(() ->  new CoffeeBeanNotFoundException(id));
    coffeeBean.delete();
    coffeeBeanRepository.save(coffeeBean);
  }

}
