package com.valentine.daily_grind.repository;

import com.valentine.daily_grind.model.CoffeeBean;
import com.valentine.daily_grind.model.RoastLevel;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeBeanRepository extends JpaRepository<CoffeeBean, Long> {
  Optional<CoffeeBean> findByIdAndDeletedAtIsNull(Long id);
  Page<CoffeeBean> findByDeletedAtIsNull(Pageable pageable);
  Page<CoffeeBean> findByDeletedAtIsNullAndRoastLevel(RoastLevel roastLevel, Pageable pageable);
}
