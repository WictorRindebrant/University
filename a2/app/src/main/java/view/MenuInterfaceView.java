package view;

/**
 * The Interface for the MenuView Classes.
 */
public interface MenuInterfaceView {
  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Main Menu.
   */
  public static enum MainMenuChoice {
    MemberMenu,
    ItemMenu,
    ContractMenu,
    TimeMenu,
    Quit
  }

  /**
   * Prints the Main Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MainMenuChoice showMainMenu(java.util.Scanner scan);

  public String getLanguage();
}
