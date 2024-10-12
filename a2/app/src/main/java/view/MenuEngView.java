package view;

import java.util.Scanner;

/**
 * This class handles everything about the Main Menu.
 */
public class MenuEngView implements MenuInterfaceView {

  /**
   * Prints the Main Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MainMenuChoice showMainMenu(Scanner scan) {
    System.out.println("======> Main Menu <======");
    System.out.println("[1] Member Menu");
    System.out.println("[2] Item Menu");
    System.out.println("[3] Contract Menu");
    System.out.println("[4] Next Day");
    System.out.println("====== [q] to exit ======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return MainMenuChoice.MemberMenu;

    } else if (option.equals("2")) {
      return MainMenuChoice.ItemMenu;

    } else if (option.equals("3")) {
      return MainMenuChoice.ContractMenu;

    } else if (option.equals("4")) {
      return MainMenuChoice.TimeMenu;

    } else {
      return MainMenuChoice.Quit;
    }
  }

  @Override
  public String getLanguage() {
    return "Eng";
  }
}
