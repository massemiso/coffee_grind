package com.valentine.daily_grind.dto;

import com.valentine.daily_grind.model.Roaster;
import org.springframework.stereotype.Component;

@Component
public class RoasterMapper {

  public RoasterResponseDto toDto(Roaster roaster) {
    return new RoasterResponseDto(
        roaster.getId(),
        roaster.getName(),
        roaster.getLocation(),
        roaster.getYearEstablished(),
        (roaster.getDeletedAt() == null)
    );
  }

  public Roaster toEntity(RoasterRequestDto roasterRequestDto) {
    Roaster roaster = new Roaster();
    roaster.setName(roasterRequestDto.name());
    roaster.setLocation(roasterRequestDto.location());
    roaster.setYearEstablished(roasterRequestDto.yearEstablished());
    return roaster;
  }
}
