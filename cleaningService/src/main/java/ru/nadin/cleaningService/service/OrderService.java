package ru.nadin.cleaningService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nadin.cleaningService.data.model.AdditionalCleaningFavourModel;
import ru.nadin.cleaningService.data.model.CleaningTypeModel;
import ru.nadin.cleaningService.data.model.OrderModel;
import ru.nadin.cleaningService.data.repository.OrderRepository;
import ru.nadin.cleaningService.service.dto.OrderDto;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderService {
  private final CleaningTypeService cleaningTypeService;
  private final AdditionalCleaningFavourService additionalCleaningFavourService;

  private final OrderRepository orderRepository;

  public OrderModel createOrder(final OrderDto orderDto) {
    OrderModel orderModel = new OrderModel();
    orderModel.setAddress(orderDto.getAddress());
    orderModel.setCleaningDate(orderDto.getCleaningDate());
    orderModel.setCustomersName(orderDto.getCustomersName());
    orderModel.setPhoneNumber(orderDto.getPhoneNumber());
    orderModel.setCustomersSurname(orderDto.getCustomersSurname());
    double finalCost = 0;

    Set<CleaningTypeModel> cleaningTypeModels = new LinkedHashSet<>();
    for (Long cleaningTypeId : orderDto.getCleaningTypesId()) {
      CleaningTypeModel cleaningTypeModel = cleaningTypeService.searchCleaningTypeById(cleaningTypeId);
      cleaningTypeModels.add(cleaningTypeModel);
      finalCost += cleaningTypeModel.getPrice();
    }
    orderModel.setCleaningTypes(cleaningTypeModels);

    Set<AdditionalCleaningFavourModel> additionalCleaningFavourModels = new LinkedHashSet<>();
    for (Long additionalFavourId : orderDto.getAdditionalCleaningFavoursId()) {
      AdditionalCleaningFavourModel additionalCleaningFavourModel
        = additionalCleaningFavourService.searchAdditionalFavourById(additionalFavourId);
      additionalCleaningFavourModels.add(additionalCleaningFavourModel);
      finalCost += additionalCleaningFavourModel.getPrice();
    }
    orderModel.setAdditionalCleaningFavours(additionalCleaningFavourModels);
    orderModel.setFinalCost(finalCost);
    return orderRepository.save(orderModel);
  }

  public OrderModel findOrderById(final Long id) {
    return orderRepository.findById(id)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с ID - " + id + " не существует!")
      );
  }

  public OrderModel findOrderByPhoneNumber(final String phoneNumber) {
    return orderRepository.findByPhoneNumberIsLikeIgnoreCase(phoneNumber)
      .orElseThrow(
        () -> new IllegalArgumentException("Записи с номером телефона заказчика - " + phoneNumber + " не существует!")
      );
  }

  public List<OrderModel> findAllOrders() {
    return orderRepository.findAll();
  }

  public void deleteOrderByPhoneNumber(final String phoneNumber) {
    orderRepository.deleteByPhoneNumberIsLikeIgnoreCase(phoneNumber);
  }

  public void deleteOrderById(final Long id) {
    orderRepository.deleteById(id);
  }
}
