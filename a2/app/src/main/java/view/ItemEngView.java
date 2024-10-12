package view;

import java.util.Scanner;
import model.Game;
import model.ItemObject;
import model.Other;
import model.Sport;
import model.Tool;
import model.Toy;
import model.Vehicel;

/**
 * This class handles everything about item creation.
 */
public class ItemEngView implements ItemInterfaceView {

  /**
   * Prints the Item Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ItemMenuChoice showItemMenu(Scanner scan) {
    System.out.println("======> Item Menu <======");
    System.out.println("[1] Add Item");
    System.out.println("[2] Delete Item");
    System.out.println("[3] Change Item Info");
    System.out.println("[4] View Item Info");
    System.out.println("======= [q] to exit =======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return ItemMenuChoice.AddItem;

    } else if (option.equals("2")) {
      return ItemMenuChoice.DeleteItem;

    } else if (option.equals("3")) {
      return ItemMenuChoice.ChangeItemInfo;

    } else if (option.equals("4")) {
      return ItemMenuChoice.ViewItemInfo;

    } else {
      return ItemMenuChoice.Quit;

    }
  }

  /**
   * Prints the Edit Item Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ItemEditMenuChoice showEditItemMenu(Scanner scan) {
    System.out.println("======> Edit Item <======");
    System.out.println("[1] New name");
    System.out.println("[2] New description");
    System.out.println("[3] New cost per day");
    System.out.println("====== [q] to exit ======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return ItemEditMenuChoice.NewName;

    } else if (option.equals("2")) {
      return ItemEditMenuChoice.NewDescription;

    } else if (option.equals("3")) {
      return ItemEditMenuChoice.NewCostDay;

    } else {
      return ItemEditMenuChoice.Quit;

    }
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newTool(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Tool newItem = new Tool(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newVehicel(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Vehicel newItem = new Vehicel(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newGame(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Game newItem = new Game(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newToy(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Toy newItem = new Toy(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newSport(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Sport newItem = new Sport(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public ItemObject newOther(int creationDay, Scanner scan) {
    String name = newName(scan);
    String desc = newDescription(scan);
    int costDay = newCostDay(scan);
    Other newItem = new Other(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Let the user decide a name and then returns that name.
   *
   * @param scan Handles the inputs
   * @return Returns the name as a String.
   */
  public String newName(Scanner scan) {
    System.out.println("Enter name: ");
    String name = scan.nextLine();
    return name;
  }

  /**
   * Let the user decide a description and then returns that description.
   *
   * @param scan Handles the inputs
   * @return Returns the description as a String.
   */
  public String newDescription(Scanner scan) {
    System.out.println("Enter a description: ");
    String desc = scan.nextLine();
    return desc;
  }

  /**
   * Let the user decide a cost per day and then returns that cost per day.
   *
   * @param scan Handles the inputs
   * @return Returns the cost per day as an int.
   */
  public int newCostDay(Scanner scan) {
    System.out.println("Enter the cost per day: ");
    int costDay = Integer.parseInt(scan.nextLine());
    return costDay;
  }

  public ItemObject newToolHardcode(String name, String desc, int costDay, int creationDay) {
    Tool newItem = new Tool(name, desc, costDay, creationDay);
    return newItem;
  }

  public ItemObject newVehicelHardcode(String name, String desc, int costDay, int creationDay) {
    Vehicel newItem = new Vehicel(name, desc, costDay, creationDay);
    return newItem;
  }

  public ItemObject newGameHardcode(String name, String desc, int costDay, int creationDay) {
    Game newItem = new Game(name, desc, costDay, creationDay);
    return newItem;
  }

  public ItemObject newToyHardcode(String name, String desc, int costDay, int creationDay) {
    Toy newItem = new Toy(name, desc, costDay, creationDay);
    return newItem;
  }

  public ItemObject newSportHardcode(String name, String desc, int costDay, int creationDay) {
    Sport newItem = new Sport(name, desc, costDay, creationDay);
    return newItem;
  }

  public ItemObject newOtherHardcode(String name, String desc, int costDay, int creationDay) {
    Other newItem = new Other(name, desc, costDay, creationDay);
    return newItem;
  }

  /**
   * Sub menu options.
   */
  public ItemCategoryMenuChoice whatTypeOfItem(Scanner scan) {
    System.out.println("What type of object do you want to add?");
    System.out.println("[1] Tool");
    System.out.println("[2] Vehicel");
    System.out.println("[3] Game");
    System.out.println("[4] Toy");
    System.out.println("[5] Sport");
    System.out.println("[6] Other");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return ItemCategoryMenuChoice.Tool;

    } else if (option.equals("2")) {
      return ItemCategoryMenuChoice.Vehicel;

    } else if (option.equals("3")) {
      return ItemCategoryMenuChoice.Game;

    } else if (option.equals("4")) {
      return ItemCategoryMenuChoice.Toy;

    } else if (option.equals("5")) {
      return ItemCategoryMenuChoice.Sport;

    } else if (option.equals("6")) {
      return ItemCategoryMenuChoice.Other;

    } else {
      return ItemCategoryMenuChoice.Quit;

    }
  }

  public void numberOfItemsowned(int nmrOfItems) {
    System.out.println("Number of items: " + nmrOfItems);
  }

  public void lineBreaker() {
    System.out.println("------------------------");
  }

  public void ownedItems() {
    System.out.println("Owned items:");
  }

  public void nameOfItem(String item) {
    System.out.println("> " + item);
  }

  public void chooseWhatToChange() {
    System.out.println("Choose what to change");
  }

  public void chooseAnItem() {
    System.out.println("Choose an item: ");
  }

  public void enterDayOfCreation() {
    System.out.println("Enter day of creation: ");
  }

  public void description(String description) {
    System.out.println("Description: " + description);
  }

  public void costPerDay(int costperday) {
    System.out.println("Cost per day: " + costperday);
  }

  public void dayOfCreation(int creation) {
    System.out.println("Day of creation: " + creation);
  }

  public void historyAndFuture() {
    System.out.println("Items history and future: ");
  }

  public void choosenMemberItems(int ind, String item) {
    System.out.println(ind + ". " + item);
  }

  public void errorMessage() {
    System.err.println("ERROR! Invalid input.");
  }

  public void errorMessageDup() {
    System.out.println("ERROR! Name alredy taken");
  }
}