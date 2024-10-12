package view;

/**
 * The Interface for the ContractView Classes.
 */
public interface ContractInterfaceView {

  public view.ContractEngView.ContractMenuChoice showContractMenu(java.util.Scanner scan);

  public model.Contract newContract(int startDay, int endDay, model.Member member, model.ItemObject item);

  public void lendTo(String receiver);

  public void lendBetweenDays(int startDay, int endDay);

  public void lendByAndBetweendays(String lent, int startDay, int endDay);

  public void chooseStartOfTheDayContract();

  public void chooseEndOfTheContract();

  public void chooseWhatItem();

  public void whoWantsToLendItem();

  public void indexAndName(int index, String names);

  public void doesNotHaveFunds(int totalCost, String name, int cash);

  public void infoAboutContract(String name, String item, int startday, int endday);

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Contract Menu.
   */
  public static enum ContractMenuChoice {
    AddContract,
    ListContract,
    Quit
  }

}
