package view;

/**
 * The Interface for the ItemView Classes.
 */
public interface ItemInterfaceView {

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Item Menu.
   */
  public static enum ItemMenuChoice {
    AddItem,
    DeleteItem,
    ChangeItemInfo,
    ViewItemInfo,
    Quit
  }

  /**
   * Prints the Item Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ItemMenuChoice showItemMenu(java.util.Scanner scan);

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Edit Item Menu.
   */
  public static enum ItemEditMenuChoice {
    NewName,
    NewDescription,
    NewCostDay,
    Quit
  }

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Category Item Menu.
   */
  public static enum ItemCategoryMenuChoice {
    Tool,
    Vehicel,
    Game,
    Toy,
    Sport,
    Other,
    Quit
  }

  /**
   * Prints the Edit Item Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ItemEditMenuChoice showEditItemMenu(java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newTool(int creationDay, java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newVehicel(int creationDay, java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newGame(int creationDay, java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newToy(int creationDay, java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newSport(int creationDay, java.util.Scanner scan);

  /**
   * Creates a new item depending on what is sent in to the function and some
   * inputs.
   * Then returns that item.
   *
   * @param creationDay The day when item is created
   * @param scan        Handles inputs
   * @return Returning the new created ItemObject.
   */
  public model.ItemObject newOther(int creationDay, java.util.Scanner scan);

  /**
   * Let the user decide a name and then returns that name.
   *
   * @param scan Handles the inputs
   * @return Returns the name as a String.
   */
  public String newName(java.util.Scanner scan);

  /**
   * Let the user decide a description and then returns that description.
   *
   * @param scan Handles the inputs
   * @return Returns the description as a String.
   */
  public String newDescription(java.util.Scanner scan);

  /**
   * Let the user decide a cost per day and then returns that cost per day.
   *
   * @param scan Handles the inputs
   * @return Returns the cost per day as an int.
   */
  public int newCostDay(java.util.Scanner scan);

  public model.ItemObject newToolHardcode(String name, String desc, int costDay, int creationDay);

  public model.ItemObject newVehicelHardcode(String name, String desc, int costDay, int creationDay);

  public model.ItemObject newGameHardcode(String name, String desc, int costDay, int creationDay);

  public model.ItemObject newToyHardcode(String name, String desc, int costDay, int creationDay);

  public model.ItemObject newSportHardcode(String name, String desc, int costDay, int creationDay);

  public model.ItemObject newOtherHardcode(String name, String desc, int costDay, int creationDay);

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Category Item Menu.
   */
  
  public ItemCategoryMenuChoice whatTypeOfItem(java.util.Scanner scan);

  public void numberOfItemsowned(int nmrOfItems);

  public void lineBreaker();

  public void ownedItems();

  public void nameOfItem(String item);

  public void chooseWhatToChange();

  public void chooseAnItem();

  public void enterDayOfCreation();

  public void description(String description);

  public void costPerDay(int costperday);

  public void dayOfCreation(int creation);

  public void historyAndFuture();

  public void choosenMemberItems(int ind, String item);

  public void errorMessage();

  public void errorMessageDup();

}
