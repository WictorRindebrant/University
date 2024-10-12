package view;

import java.util.Scanner;
import model.Contract;
import model.ItemObject;
import model.Member;

/**
 * This class handles everything about contract creation.
 */
public class ContractSweView implements ContractInterfaceView {

  /**
   * Prints the Contract Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public ContractMenuChoice showContractMenu(Scanner scan) {
    System.out.println("======> Kontrakt Menu <======");
    System.out.println("[A] Lägg till kontrakt");
    System.out.println("[B] Visa lista av alla kontrakt");

    System.out.println("====== [q] för att avsluta ======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return ContractMenuChoice.AddContract;

    } else if (option.equals("B")) {
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
    System.out.println("  > Lånar ut till " + receiver);
  }

  public void lendBetweenDays(int startDay, int endDay) {
    System.out.println("  > Mellan dagarna " + startDay + " - " + endDay);
  }

  public void lendByAndBetweendays(String lent, int startDay, int endDay) {
    System.out.println("> lånas av " + lent + " mellan dessa dagar " + startDay + " - "
        + endDay);
  }

  public void chooseStartOfTheDayContract() {
    System.out.println("Välj dag du vill att kontraktet ska börja: ");
  }

  public void chooseEndOfTheContract() {
    System.out.println("Välj en dag när du vill att kontraktet ska gå ut: ");
  }

  public void chooseWhatItem() {
    System.out.println("Välj föremål du vill låna: ");
  }

  public void whoWantsToLendItem() {
    System.out.println("Vem vill låna detta föremål?");
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
    System.out.println("Kostnaden för alla dagar:  " + totalCost);
    System.out.println("Medlem: " + name + " har " + cash + "c");
  }

  public void infoAboutContract(String name, String item, int startday, int endday) {
    System.out.println(name + " har " + item + " mellan " + startday + " - " + endday);
  }
}
