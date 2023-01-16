package ru.nadin.cleaningService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.nadin.cleaningService.menu.MainMenu;

@SpringBootApplication
@RequiredArgsConstructor
public class CleaningServiceApplication implements CommandLineRunner {
  private final MainMenu mainMenu;

  public static void main(String[] args) {
    SpringApplication.run(CleaningServiceApplication.class, args);
  }

  @Override public void run(final String... args) throws Exception {
    mainMenu.showMainMenu();
  }
}
