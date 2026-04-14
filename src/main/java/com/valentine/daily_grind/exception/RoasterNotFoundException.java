package com.valentine.daily_grind.exception;

public class RoasterNotFoundException extends RuntimeException {

  public RoasterNotFoundException(Long id) {
    super("Could not find roaster with id " + id);
  }
}
