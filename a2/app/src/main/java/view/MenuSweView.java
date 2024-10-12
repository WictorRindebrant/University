package view;

import java.util.Scanner;

/**
 * This class handles everything about the Main Menu.
 */
public class MenuSweView implements MenuInterfaceView {

  /**
   * Prints the Main Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MainMenuChoice showMainMenu(Scanner scan) {
    System.out.println("======> Huvud meny <======");
    System.out.println("[A] Medlems Meny");
    System.out.println("[B] Föremåls meny");
    System.out.println("[C] kontrakt meny");
    System.out.println("[D] Nästa dag");
    System.out.println("====== [q] för att avsluta ======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return MainMenuChoice.MemberMenu;

    } else if (option.equals("B")) {
      return MainMenuChoice.ItemMenu;

    } else if (option.equals("C")) {
      return MainMenuChoice.ContractMenu;

    } else if (option.equals("D")) {
      return MainMenuChoice.TimeMenu;

    } else {
      return MainMenuChoice.Quit;
    }
  }

  @Override
  public String getLanguage() {
    return "Swe";
  }
}