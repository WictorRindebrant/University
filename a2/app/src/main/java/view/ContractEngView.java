package view;

import java.util.Scanner;
import model.Contract;
import model.ItemObject;
import model.Member;

/**
 * This class handles everything about contract creation.
 */
public class ContractEngView implements ContractInterfaceView {

  /**
   * Prints the Contract Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ContractMenuChoice showContractMenu(Scanner scan) {
    System.out.println("======> Contract Menu <======");
    System.out.println("[1] Add contract");
    System.out.println("[2] List contracts");

    System.out.println("====== [q] to exit ======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return ContractMenuChoice.AddContract;

    } else if (option.equals("2")) {
      return ContractMenuChoice.ListContract;

    } else {
      return ContractMenuChoice.Quit;

    }
  }

  /**
   * Creates a new contract with the decided varibles that we send in to the
   * function.
   * Then we return that created contract.
   */
  public Contract newContract(int startDay, int endDay, Member member, ItemObject item) {
    Contract newCon = new Contract(startDay, endDay, member, item);
    return newCon;
  }

  public void lendTo(String receiver) {
    System.out.println("  > Lend to " + receiver);
  }

  public void lendBetweenDays(int startDay, int endDay) {
    System.out.println("  > Between day " + startDay + " - " + endDay);
  }

  public void lendByAndBetweendays(String lent, int startDay, int endDay) {
    System.out.println("> lend by " + lent + " between days " + startDay + " - "
        + endDay);
  }

  public void chooseStartOfTheDayContract() {
    System.out.println("Choose the day you want the contract to start: ");
  }

  public void chooseEndOfTheContract() {
    System.out.println("Choose the day you want the contract to end: ");
  }

  public void chooseWhatItem() {
    System.out.println("Choose the item you want to lend: ");
  }

  public void whoWantsToLendItem() {
    System.out.println("Who is wanting to lend this item?");
  }

  public void indexAndName(int index, String names) {
    System.out.println(index + ". " + names);
  }

  /**
   * Print error message for low funds.
   *
   * @param totalCost Cost of the item.
   * @param name      Name of the member that tries to lend it.
   * @param cash      Amount of credits the member have.
   */

  public void doesNotHaveFunds(int totalCost, String name, int cash) {
    System.out.println("That member dosent have the funds to rent that item");
    System.out.println("Item cost for all day " + totalCost);
    System.out.println("Member: " + name + " have " + cash + "c");
  }

  public void infoAboutContract(String name, String item, int startday, int endday) {
    System.out.println(name + " have " + item + " between " + startday + " - " + endday);
  }
}
