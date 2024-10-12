package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Manager;
import model.Member;

/**
 * This class handles everything about Member creation.
 */
public class MemberSweView implements MemberInterfaceView {

  /**
   * Prints the Member Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MemberMenuChoice showMemberMenu(Scanner scan) {
    System.out.println("======> Medlems meny <======");
    System.out.println("[A] Lägg till medlem");
    System.out.println("[B] Medlems information");
    System.out.println("[C] Ta bort medlem");
    System.out.println("[D] Ändra medlems information");
    System.out.println("[E] Simpel medlems lista");
    System.out.println("[F] Detaljerad medlems lista");
    System.out.println("====== [q] för att avsluta ======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return MemberMenuChoice.AddMember;

    } else if (option.equals("B")) {
      return MemberMenuChoice.MemberInfo;

    } else if (option.equals("C")) {
      return MemberMenuChoice.DeleteMember;

    } else if (option.equals("D")) {
      return MemberMenuChoice.ChangeMemberInfo;

    } else if (option.equals("E")) {
      return MemberMenuChoice.SimpleMemberList;

    } else if (option.equals("F")) {
      return MemberMenuChoice.VerboseMemberList;

    } else {
      return MemberMenuChoice.Quit;

    }
  }

  /**
   * Prints the Edit Member Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MemberEditMenuChoice showMemberEditMenu(Scanner scan) {
    System.out.println("======> Ändra medlem <======");
    System.out.println("[A] Nytt namn");
    System.out.println("[B] Nytt telefon nummer");
    System.out.println("[C] Ny email");
    System.out.println("======= [q] för att avsluta =======");
    String option = scan.nextLine();

    if (option.equals("A")) {
      return MemberEditMenuChoice.NewName;

    } else if (option.equals("B")) {
      return MemberEditMenuChoice.NewPhonenmr;

    } else if (option.equals("C")) {
      return MemberEditMenuChoice.NewEmail;

    } else {
      return MemberEditMenuChoice.Quit;

    }
  }

  /**
   * Creates a new Member depending on what is sent in to the function and some
   * inputs.
   * Then returns that Member.
   *
   * @param scan Handles the inputs.
   * @param man  Handles the inputs.
   * @return Returns the Member.
   */
  public Member newMember(Scanner scan, Manager man) {
    Member newMember = new Member();
    newMember.setName(newName(scan));
    newMember.setPhonenmr(phoneCheck(scan, newMember, man, newPhonenmr(scan)));
    newMember.setEmail(emailCheck(scan, newMember, man, newEmail(scan)));
    newMember.setCreationDay(man.getToDay());
    newMember.setId(man.getMemberlst());

    return newMember;
  }

  public String newName(Scanner scan) {
    System.out.println("Ange namn: ");
    return scan.nextLine();
  }

  public String newPhonenmr(Scanner scan) {
    System.out.println("Ange telefon nummer: ");
    return scan.nextLine();
  }

  public String newEmail(Scanner scan) {
    System.out.println("Ange email: ");
    return scan.nextLine();
  }

  /**
   * Lets the user enter a phone number until it gets an false value from the
   * phone number checker in the Member class.
   * HOBBE NOTE: We talked about this not being an view respeons but did not have to fix it.
   *
   * @param scan          Handles inputs.
   * @param choosenMember Targeted Member.
   * @param man           Manager class. Contains member list
   * @param phonenmr      Members phone number.
   * @return the phone number.
   */
  public String phoneCheck(Scanner scan, Member choosenMember, Manager man, String phonenmr) {
    Boolean phoneExist = choosenMember.checkPhonenmr(phonenmr, man.getMemberlst(), scan, choosenMember);
    while (phoneExist) {
      System.out.println("ERROR! Finns redan eller att rutan är tom, försök igen:");
      phonenmr = newPhonenmr(scan);
      phoneExist = choosenMember.checkPhonenmr(phonenmr, man.getMemberlst(), scan, choosenMember);
    }
    return phonenmr;
  }

  /**
   * Lets the user enter a phone number until it gets an false value from the
   * phone number checker in the Member class.
   * HOBBE NOTE: We talked about this not being an view respeons but did not have to fix it.
   *
   * @param scan          Handles inputs.
   * @param choosenMember Targeted Member.
   * @param man           Manager class. Contains member list
   * @param email         Members phone number.
   * @return the email.
   */
  public String emailCheck(Scanner scan, Member choosenMember, Manager man, String email) {
    Boolean emailExist = choosenMember.checkEmail(email, man.getMemberlst(), scan, choosenMember);
    while (emailExist) {
      System.out.println("ERROR! Finns rendan, tom rad eller saknas @, försök igen:");
      email = newEmail(scan);
      emailExist = choosenMember.checkEmail(email, man.getMemberlst(), scan, choosenMember);
    }
    return email;
  }

  public void chooseAmember() {
    System.out.println("Välj en medlem: ");
  }

  public void printName(String name) {
    System.out.println("Namn: " + name);
  }

  public void memberInfoEmail(String email) {
    System.out.println("Email: " + email);
  }

  public void memberInfoPhoneNumber(String phone) {
    System.out.println("Mobilnummer: " + phone);
  }

  public void memberInfoId(String id) {
    System.out.println("Id: " + id);
  }

  public void memberInfoCash(Integer cash) {
    System.out.println("pengar: " + cash);
  }

  public void indexAndNameAndId(Integer index, String names, String id) {
    System.out.println(index + ". " + names + " - " + id);
  }

  public void errorMessage() {
    System.err.println("ERROR! felaktig input.");
  }

  public ArrayList<Member> getSortedMemberList(Manager manager) {
    return manager.getMemberlstSortedId();
  }
}
