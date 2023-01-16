package ru.nadin.cleaningService.service.dto;

import lombok.Data;

@Data
public class CleaningTypeDto {
  private String title;
  private String description;
  private double price;
}
