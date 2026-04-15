package com.valentine.grind.exception;

public class CoffeeBeanNotFoundException extends RuntimeException{
  public CoffeeBeanNotFoundException(Long id) {
    super("Coffee bean with " + id + " not found");
  }

}
