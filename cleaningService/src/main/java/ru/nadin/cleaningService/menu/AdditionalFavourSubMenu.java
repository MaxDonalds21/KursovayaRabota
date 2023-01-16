package ru.nadin.cleaningService.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nadin.cleaningService.data.model.AdditionalCleaningFavourModel;
import ru.nadin.cleaningService.service.AdditionalCleaningFavourService;
import ru.nadin.cleaningService.service.dto.AdditionalFavourDto;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class AdditionalFavourSubMenu {
  private static final Scanner scanner = new Scanner(System.in);

  private final AdditionalCleaningFavourService additionalCleaningFavourService;

  void showMenu() {
    while (true) {
      System.out.println("\nМеню дополнительных услуг");
      System.out.println("Выберите действие: ");
      System.out.println("(1)Добавить услугу");
      System.out.println("(2)Редактировать существующую услугу");
      System.out.println("(3)Удалить услугу");
      System.out.println("(4)Поиск услуги");
      System.out.println("(5)Вывести все доступные услуги");
      System.out.println("(6)Вернуться в главное меню");
      int select;
      try {
        select = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        select = 999;
      }
      switch (select) {
        case 1:
          createAdditionalCleaningFavourMenu();
          break;
        case 2:
          updateAdditionalCleaningFavourMenu();
          break;
        case 3:
          deleteAdditionalCleaningFavourMenu();
          break;
        case 4:
          searchAdditionalCleaningFavourMenu();
          break;
        case 5:
          findAllAdditionalCleaningFavourMenu();
          break;
        case 6:
          return;
        default:
          System.out.println("Такой операции не существует!\nПопробуйте снова");
      }
    }
  }

  protected void findAllAdditionalCleaningFavourMenu() {
    List<AdditionalCleaningFavourModel> additionalFavours = additionalCleaningFavourService.findAdditionalFavours();
    if (additionalFavours.isEmpty()) {
      System.out.println("Список пуст!");
    } else {
      for (AdditionalCleaningFavourModel model : additionalFavours) {
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

  private void searchAdditionalCleaningFavourMenu() {
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
        System.out.println("Введите ID дополнительной услуги:");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println(
          "Запись найдена: \n"
            + additionalCleaningFavourService.searchAdditionalFavourById(id)
        );
      }
      case 2 -> {
        System.out.println("Введите название дополнительной услуги:");
        String title = scanner.nextLine();
        System.out.println(
          "Запись найдена: \n"
            + additionalCleaningFavourService.searchAdditionalFavourByTitle(title)
        );
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void deleteAdditionalCleaningFavourMenu() {
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
        additionalCleaningFavourService.deleteAdditionalFavourById(id);
      }
      case 2 -> {
        System.out.println("Введите название типа клининга:");
        String title = scanner.nextLine();
        additionalCleaningFavourService.deleteAdditionalFavourByTitle(title);
        System.out.println("Запись удалена!");
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void updateAdditionalCleaningFavourMenu() {
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
        AdditionalFavourDto additionalFavourDto = getAdditionalFavourDto();
        System.out.println(
          "Запись изменена: \n"
            + additionalCleaningFavourService.updateAdditionalFavourById(additionalFavourDto, id)
        );
      }
      case 2 -> {
        System.out.println("Введите название дополнительной услуги:");
        String title = scanner.nextLine();
        AdditionalFavourDto additionalFavourDto = getAdditionalFavourDto();
        System.out.println(
          "Запись изменена: \n"
            + additionalCleaningFavourService.updateAdditionalFavourByTitle(additionalFavourDto, title)
        );
      }
      default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
    }
  }

  private void createAdditionalCleaningFavourMenu() {
    AdditionalFavourDto additionalFavourDto = getAdditionalFavourDto();
    System.out.println(
      "Новая дополнительная услуга - "
        + additionalCleaningFavourService.createAdditionalFavour(additionalFavourDto)
    );
  }

  private AdditionalFavourDto getAdditionalFavourDto() {
    AdditionalFavourDto additionalFavourDto = new AdditionalFavourDto();
    System.out.println("\nЗаполните информацию о дополнительной услуге");
    System.out.println("Название: ");
    additionalFavourDto.setTitle(scanner.nextLine());
    System.out.println("Цена: ");
    String price = scanner.nextLine();
    if (price != null && !price.equalsIgnoreCase("")) {
      additionalFavourDto.setPrice(Double.parseDouble(price));
    }
    return additionalFavourDto;
  }
}
