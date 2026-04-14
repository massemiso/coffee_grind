package com.valentine.daily_grind.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roasters")
@Getter @Setter @NoArgsConstructor
public class Roaster {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String location;
  private Integer yearEstablished;
  private LocalDateTime deletedAt;

  public void delete() {
    setDeletedAt(LocalDateTime.now());
  }
}
