package ru.nadin.cleaningService.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nadin.cleaningService.data.model.OrderModel;
import ru.nadin.cleaningService.service.OrderService;
import ru.nadin.cleaningService.service.dto.OrderDto;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OrderSubMenu {
  private static final Scanner scanner = new Scanner(System.in);
  private final AdditionalFavourSubMenu additionalFavourSubMenu;
  private final CleaningTypeSubMenu cleaningTypeSubMenu;
  private final OrderService orderService;

  void showMenu() {
    while (true) {
      System.out.println("\nМеню управления заказами");
      System.out.println("Выберите действие: ");
      System.out.println("(1)Создать заказ");
      System.out.println("(2)Удалить заказ");
      System.out.println("(3)Найти заказ");
      System.out.println("(4)Вывести все заказы");
      System.out.println("(5)Вернуться в главное меню");
      int select;
      try {
        select = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        select = 999;
      }
      switch (select) {
        case 1:
          createOrderMenu();
          break;
        case 2:
          deleteOrderMenu();
          break;
        case 3:
          searchOrderMenu();
          break;
        case 4:
          findAllOrdersMenu();
          break;
        case 5:
          return;
        default:
          System.out.println("Такой операции не существует!\nПопробуйте снова");
      }
    }
  }

  private void createOrderMenu() {
    System.out.println("\nЗаявка на уборку: ");
    try {
      OrderDto orderDto = new OrderDto();
      Set<Long> cleaningTypes = new HashSet<>();
      Set<Long> additionalFavours = new HashSet<>();
      System.out.println("Адрес места уборки:");
      String address = scanner.nextLine();
      orderDto.setAddress(address);
      System.out.println("Номер телефона заказчика: ");
      orderDto.setPhoneNumber(scanner.nextLine());
      System.out.println("Имя заказчика: ");
      orderDto.setCustomersName(scanner.nextLine());
      System.out.println("Фамилия заказчика: ");
      orderDto.setCustomersSurname(scanner.nextLine());
      System.out.println("Желаемая дата уборки (формат - YYYY-mm-dd): ");
      orderDto.setCleaningDate(LocalDate.parse(scanner.nextLine()));
      cleaningTypeSubMenu.findAllCleaningTypeMenu();
      System.out.println(
        "Введите ID типов уборки которые нужно добавить в заказ (выйти - \"0\"): ");
      while (true) {
        String select = scanner.nextLine();
        if (select.equalsIgnoreCase("0")) {
          break;
        } else {
          cleaningTypes.add(Long.parseLong(select));
        }
      }
      additionalFavourSubMenu.findAllAdditionalCleaningFavourMenu();
      System.out.println(
        "Введите ID дополнительных услуг которые нужно добавить в заказ (выйти - \"0\"): ");
      while (true) {
        String select = scanner.nextLine();
        if (select.equalsIgnoreCase("0")) {
          break;
        } else {
          additionalFavours.add(Long.parseLong(select));
        }
      }
      orderDto.setCleaningTypesId(cleaningTypes);
      orderDto.setAdditionalCleaningFavoursId(additionalFavours);
      OrderModel order = orderService.createOrder(orderDto);
      System.out.println("ID: "
        + order.getId()
        + "; Адрес уборки: "
        + order.getAddress()
        + "; Сумма заказа: "
        + order.getFinalCost()
        + "; Номер телефона заказчика: "
        + order.getPhoneNumber()
        + ";");

    } catch (NumberFormatException e) {
      System.out.println("Неверные параметры ввода!");
    }
  }

  private void deleteOrderMenu() {
    System.out.println("Найти запись по:");
    System.out.println("(1) ID");
    System.out.println("(2) Номеру телефона заказчика");
    int select;
    try {
      select = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      select = 999;
    }
    switch (select) {
      case 1 -> {
        System.out.println("Введите ID заказа:");
        Long id = Long.parseLong(scanner.nextLine());
        orderService.deleteOrderById(id);
        System.out.println("Запись удалена!");
      }
      case 2 -> {
        System.out.println("Введите номер телефона:");
        String phoneNumber = scanner.nextLine();
        orderService.deleteOrderByPhoneNumber(phoneNumber);
        System.out.println("Запись удалена!");
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void searchOrderMenu() {
    System.out.println("Найти запись по:");
    System.out.println("(1) ID");
    System.out.println("(2) Номеру телефона заказчика");
    int select;
    try {
      select = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      select = 999;
    }
    switch (select) {
      case 1 -> {
        System.out.println("Введите ID заказа:");
        Long id = Long.parseLong(scanner.nextLine());
        showOrderInfo(orderService.findOrderById(id));
      }
      case 2 -> {
        System.out.println("Введите номер телефона:");
        String phoneNumber = scanner.nextLine();
        showOrderInfo(orderService.findOrderByPhoneNumber(phoneNumber));
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void findAllOrdersMenu() {
    List<OrderModel> orderModelList = orderService.findAllOrders();
    double totalCost = 0;
    if (!(orderModelList.isEmpty())) {

      for (OrderModel order : orderModelList) {
        totalCost += order.getFinalCost();
        System.out.println("ID: "
          + order.getId()
          + "; Адрес уборки: "
          + order.getAddress()
          + "; Сумма заказа: "
          + order.getFinalCost()
          + "; Номер телефона заказчика: "
          + order.getPhoneNumber()
          + ";");
      }
      System.out.println("Сумма всех заказов: " + totalCost + " рублей;");
    } else {
      System.out.println("Список пуст!");
    }
  }

  private void showOrderInfo(OrderModel order) {
    if (order != null) {
      System.out.println("Запись найдена: \n");
      System.out.println("ID: "
        + order.getId()
        + "; \nАдрес уборки: "
        + order.getAddress()
        + "; \nДата уборки: "
        + order.getCleaningDate()
        + "; \nСумма заказа: "
        + order.getFinalCost()
        + "; \nНомер телефона заказчика: "
        + order.getPhoneNumber()
        + "; \nФамилия заказчика: "
        + order.getCustomersSurname()
        + "; \nИмя заказчика: "
        + order.getCustomersName()
        + ";");
      System.out.println("Список услуг: ");
      order.getAdditionalCleaningFavours().forEach(System.out::println);
      System.out.println("Типы уборки: ");
      order.getCleaningTypes().forEach(System.out::println);
    }
  }
}
