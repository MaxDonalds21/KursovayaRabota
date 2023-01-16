package ru.nadin.cleaningService.data.model;

import lombok.Data;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@ToString
@Table(name = "orders")
public class OrderModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "customers_name")
  private String customersName;
  @Column(name = "customers_surname")
  private String customersSurname;
  @Column(name = "cleaning_date")
  private LocalDate cleaningDate;
  @Column(name = "address")
  private String address;
  @Column(name = "final_cost")
  private double finalCost;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "cleaning_types_orders",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "cleaning_type_id"))
  private Set<CleaningTypeModel> cleaningTypes = new LinkedHashSet<>();

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "additional_cleaning_favours_orders",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "additional_cleaning_favour_id"))
  private Set<AdditionalCleaningFavourModel> additionalCleaningFavours = new LinkedHashSet<>();
}
