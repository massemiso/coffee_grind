package com.valentine.grind.service;

import static org.junit.jupiter.api.Assertions.*;

import com.valentine.grind.dto.CoffeeBeanRequestDto;
import com.valentine.grind.dto.CoffeeBeanResponseDto;
import com.valentine.grind.model.Roaster;
import com.valentine.grind.repository.RoasterRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CoffeeBeanServiceTest {

  @Autowired
  private CoffeeBeanService coffeeBeanService;

  @Autowired
  private RoasterRepository roasterRepository;

  @Test
  void shouldCreateAndRetrieveCoffeeBean() {
    // 1. Setup: Create a Roaster first (since beans need one)
    Roaster roaster = new Roaster();
    roaster.setName("Test Roastery");
    roaster.setLocation("Testing City");
    roaster.setYearEstablished(2020);
    roaster = roasterRepository.save(roaster);

    // 2. Action: Create a Bean
    CoffeeBeanRequestDto request = new CoffeeBeanRequestDto(
        "Test Espresso", "Brazil", "DARK",
        new BigDecimal("25.00"), 100, roaster.getId()
    );
    CoffeeBeanResponseDto savedBean = coffeeBeanService.create(request);

    // 3. Verify
    assertNotNull(savedBean.id());
    assertEquals("Test Espresso", savedBean.name());
    assertEquals("DARK", savedBean.roastLevel());
  }
}