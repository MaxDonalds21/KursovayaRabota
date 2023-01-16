package ru.nadin.cleaningService.service.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderDto {
  private String phoneNumber;
  private String customersName;
  private String customersSurname;
  private LocalDate cleaningDate;
  private String address;
  private Set<Long> cleaningTypesId;
  private Set<Long> additionalCleaningFavoursId;
}
