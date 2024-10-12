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
public class ItemSweView implements ItemInterfaceView {

  /**
   * Prints the Item Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ItemMenuChoice showItemMenu(Scanner scan) {
    System.out.println("======> Föremåls meny <======");
    System.out.println("[A] Lägg till föremål");
    System.out.println("[B] Ta bort föremål");
    System.out.println("[C] Ändra information om ett föremål");
    System.out.println("[D] Visa information om föremål");
    System.out.println("======= [q] för att avlsuta =======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return ItemMenuChoice.AddItem;

    } else if (option.equals("B")) {
      return ItemMenuChoice.DeleteItem;

    } else if (option.equals("C")) {
      return ItemMenuChoice.ChangeItemInfo;

    } else if (option.equals("D")) {
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
    System.out.println("======> Ändra föremål <======");
    System.out.println("[A] Nytt namn");
    System.out.println("[B] Ny beskrivning");
    System.out.println("[C] Ny kostnad per dag");
    System.out.println("====== [q] för att avsluta ======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return ItemEditMenuChoice.NewName;

    } else if (option.equals("B")) {
      return ItemEditMenuChoice.NewDescription;

    } else if (option.equals("C")) {
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
    System.out.println("Ange namn: ");
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
    System.out.println("Ange beskrivning: ");
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
    System.out.println("Ange kostnad per dag: ");
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
    System.out.println("Vad för typ av föremål vill du lägga till?");
    System.out.println("[A] Verktyg");
    System.out.println("[B] Fordon");
    System.out.println("[C] Spel");
    System.out.println("[D] Leksak");
    System.out.println("[E] Sport");
    System.out.println("[F] Annat");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return ItemCategoryMenuChoice.Tool;

    } else if (option.equals("B")) {
      return ItemCategoryMenuChoice.Vehicel;

    } else if (option.equals("C")) {
      return ItemCategoryMenuChoice.Game;

    } else if (option.equals("C")) {
      return ItemCategoryMenuChoice.Toy;

    } else if (option.equals("C")) {
      return ItemCategoryMenuChoice.Sport;

    } else if (option.equals("C")) {
      return ItemCategoryMenuChoice.Other;

    } else {
      return ItemCategoryMenuChoice.Quit;

    }
  }

  public void numberOfItemsowned(int nmrOfItems) {
    System.out.println("Antal av föremål: " + nmrOfItems);
  }

  public void lineBreaker() {
    System.out.println("------------------------");
  }

  public void ownedItems() {
    System.out.println("Föremål som ägs:");
  }

  public void nameOfItem(String item) {
    System.out.println("> " + item);
  }

  public void chooseWhatToChange() {
    System.out.println("Välj vad du vill ändra: ");
  }

  public void chooseAnItem() {
    System.out.println("Välj ett föremål: ");
  }

  public void enterDayOfCreation() {
    System.out.println("Ange dag av skapande: ");
  }

  public void description(String description) {
    System.out.println("Beskrivning: " + description);
  }

  public void costPerDay(int costperday) {
    System.out.println("Kostnad per dag: " + costperday);
  }

  public void dayOfCreation(int creation) {
    System.out.println("Dagen den skapades: " + creation);
  }

  public void historyAndFuture() {
    System.out.println("Föremålets historia och framtid: ");
  }

  public void choosenMemberItems(int ind, String item) {
    System.out.println(ind + ". " + item);
  }

  public void errorMessage() {
    System.err.println("ERROR! felaktig inmatning");
  }

  public void errorMessageDup() {
    System.out.println("ERROR! Namnet finns redan");
    
  }
}