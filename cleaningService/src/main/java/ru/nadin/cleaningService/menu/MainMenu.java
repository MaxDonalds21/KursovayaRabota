package ru.nadin.cleaningService.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MainMenu {
  private static final Scanner scanner = new Scanner(System.in);
  private final CleaningTypeSubMenu cleaningTypeSubMenu;
  private final AdditionalFavourSubMenu additionalFavourSubMenu;
  private final OrderSubMenu orderSubMenu;

  public void showMainMenu() {

    while (true) {
      System.out.println("\nГлавное меню ");
      System.out.println("Выберите действие: ");
      System.out.println("(1)Типы клининга");
      System.out.println("(2)Дополнительные услуги");
      System.out.println("(3)Заказы");
      System.out.println("(4)Завершить работу ");
      int select;
      try {
        select = Integer.parseInt(scanner.nextLine());
      } catch (NumberFormatException e) {
        select = 999;
      }
      switch (select) {
        case 1 -> cleaningTypeSubMenu.showMenu();
        case 2 -> additionalFavourSubMenu.showMenu();
        case 3 -> orderSubMenu.showMenu();
        case 4 -> System.exit(0);
        default -> System.out.println("Такой операции не существует!\nПопробуйте снова");
      }
    }
  }
}
