package com.valentine.grind.dto;

import com.valentine.grind.model.CoffeeBean;
import com.valentine.grind.model.RoastLevel;
import com.valentine.grind.model.Roaster;
import org.springframework.stereotype.Component;

@Component
public class CoffeeBeanMapper {

  public CoffeeBean toEntity(CoffeeBeanRequestDto coffeeBeanRequestDto, Roaster roaster) {
    CoffeeBean coffeeBean = new CoffeeBean();
    coffeeBean.setName(coffeeBeanRequestDto.name());
    coffeeBean.setOrigin(coffeeBeanRequestDto.origin());
    coffeeBean.setRoastLevel(
        RoastLevel.valueOf(coffeeBeanRequestDto.roastLevel().toUpperCase()));
    coffeeBean.setPricePerKg(coffeeBeanRequestDto.pricePerKg());
    coffeeBean.setStockQuantity(coffeeBeanRequestDto.stockQuantity());
    coffeeBean.setRoaster(roaster);
    return coffeeBean;
  }

  public CoffeeBeanResponseDto toDto(CoffeeBean coffeeBean, RoasterResponseDto roasterResponseDto) {
    return new CoffeeBeanResponseDto(
        coffeeBean.getId(),
        coffeeBean.getName(),
        coffeeBean.getOrigin(),
        coffeeBean.getRoastLevel().toString(),
        coffeeBean.getPricePerKg(),
        coffeeBean.getStockQuantity(),
        roasterResponseDto
    );
  }

}
