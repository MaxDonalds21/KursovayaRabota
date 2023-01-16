package ru.nadin.cleaningService.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nadin.cleaningService.data.model.CleaningTypeModel;
import ru.nadin.cleaningService.service.CleaningTypeService;
import ru.nadin.cleaningService.service.dto.CleaningTypeDto;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CleaningTypeSubMenu {
  private static final Scanner scanner = new Scanner(System.in);
  private final CleaningTypeService cleaningTypeService;

  void showMenu() {
    while (true) {
      System.out.println("\nМеню типов клининга");
      System.out.println("Выберите действие: ");
      System.out.println("(1)Добавить тип клининга");
      System.out.println("(2)Редактировать существующий тип");
      System.out.println("(3)Удалить тип");
      System.out.println("(4)Поиск типа");
      System.out.println("(5)вывести все типы клининга");
      System.out.println("(6)Вернуться в главное меню");
      int select;
      try {
        select = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        select = 999;
      }
      switch (select) {
        case 1:
          createCleaningTypeMenu();
          break;
        case 2:
          updateCleaningTypeMenu();
          break;
        case 3:
          deleteCleaningTypeMenu();
          break;
        case 4:
          searchCleaningTypeMenu();
          break;
        case 5:
          findAllCleaningTypeMenu();
          break;
        case 6:
          return;
        default:
          System.out.println("Такой операции не существует!\nПопробуйте снова");
      }
    }
  }

  protected void findAllCleaningTypeMenu() {
    List<CleaningTypeModel> cleaningTypeModels = cleaningTypeService.findAllCleaningTypes();
    if (cleaningTypeModels.isEmpty()) {
      System.out.println("Список пуст!");
    } else {
      for (CleaningTypeModel model : cleaningTypeModels) {
        System.out.println("ID: "
          + model.getId()
          + "; Цена: "
          + model.getPrice()
          + "; Название: "
          + model.getTitle()
          + ";");
      }
    }
  }

  private void searchCleaningTypeMenu() {
    System.out.println("Найти запись по:");
    System.out.println("(1) ID");
    System.out.println("(2) Названию");
    int select;
    try {
      select = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      select = 999;
    }
    switch (select) {
      case 1 -> {
        System.out.println("Введите ID типа клининга:");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println(
          "Запись найдена: \n"
            + cleaningTypeService.searchCleaningTypeById(id)
        );
      }
      case 2 -> {
        System.out.println("Введите название типа клининга:");
        String title = scanner.nextLine();
        System.out.println(
          "Запись найдена: \n"
            + cleaningTypeService.searchCleaningTypeByTitle(title)
        );
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void deleteCleaningTypeMenu() {
    System.out.println("Найти запись по:");
    System.out.println("(1) ID");
    System.out.println("(2) Названию");
    int select;
    try {
      select = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      select = 999;
    }
    switch (select) {
      case 1 -> {
        System.out.println("Введите ID типа клининга:");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Запись удалена!");
        cleaningTypeService.deleteCleaningTypeById(id);
      }
      case 2 -> {
        System.out.println("Введите название типа клининга:");
        String title = scanner.nextLine();
        cleaningTypeService.deleteCleaningTypeByTitle(title);
        System.out.println("Запись удалена!");
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void updateCleaningTypeMenu() {
    System.out.println("Найти запись по:");
    System.out.println("(1) ID");
    System.out.println("(2) Названию");
    int select;
    try {
      select = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      select = 999;
    }
    switch (select) {
      case 1 -> {
        System.out.println("Введите ID типа клининга:");
        Long id = Long.parseLong(scanner.nextLine());
        CleaningTypeDto cleaningTypeDto = getCleaningTypeDto();
        System.out.println(
          "Запись изменена: \n"
            + cleaningTypeService.updateCleaningTypeById(cleaningTypeDto, id)
        );
      }
      case 2 -> {
        System.out.println("Введите название типа клининга:");
        String title = scanner.nextLine();
        CleaningTypeDto cleaningTypeDto = getCleaningTypeDto();
        System.out.println(
          "Запись изменена: \n"
            + cleaningTypeService.updateCleaningTypeByTitle(cleaningTypeDto, title)
        );
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void createCleaningTypeMenu() {
    CleaningTypeDto cleaningTypeDto = getCleaningTypeDto();
    System.out.println(
      "Новый тип клининга - "
        + cleaningTypeService.createCleaningType(cleaningTypeDto)
    );
  }

  private CleaningTypeDto getCleaningTypeDto() {
    CleaningTypeDto cleaningTypeDto = new CleaningTypeDto();
    System.out.println("\nЗаполните информацию о типе клининга");
    System.out.println("Название: ");
    cleaningTypeDto.setTitle(scanner.nextLine());
    System.out.println("Описание: ");
    cleaningTypeDto.setDescription(scanner.nextLine());
    System.out.println("Цена: ");
    String price = scanner.nextLine();
    if (price != null && !price.equalsIgnoreCase("")) {
      cleaningTypeDto.setPrice(Double.parseDouble(price));
    }
    return cleaningTypeDto;
  }
}
