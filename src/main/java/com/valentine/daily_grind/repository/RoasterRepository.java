package com.valentine.daily_grind.repository;

import com.valentine.daily_grind.model.Roaster;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoasterRepository extends JpaRepository<Roaster, Long> {
  Optional<Roaster> findByIdAndDeletedAtIsNull(Long id);
  Page<Roaster> findByDeletedAtIsNull(Pageable pageable);
  Page<Roaster> findByDeletedAtIsNullAndNameContainingIgnoreCase(String name, Pageable pageable);

}
