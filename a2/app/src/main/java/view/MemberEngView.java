package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Manager;
import model.Member;

/**
 * This class handles everything about Member creation.
 */
public class MemberEngView implements MemberInterfaceView {

  /**
   * Prints the Member Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MemberMenuChoice showMemberMenu(Scanner scan) {
    System.out.println("======> Member Menu <======");
    System.out.println("[1] Add member");
    System.out.println("[2] Member info");
    System.out.println("[3] Delete member");
    System.out.println("[4] Change member info");
    System.out.println("[5] Simpel member list");
    System.out.println("[6] Verbose member list");
    System.out.println("====== [q] to exit ======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return MemberMenuChoice.AddMember;

    } else if (option.equals("2")) {
      return MemberMenuChoice.MemberInfo;

    } else if (option.equals("3")) {
      return MemberMenuChoice.DeleteMember;

    } else if (option.equals("4")) {
      return MemberMenuChoice.ChangeMemberInfo;

    } else if (option.equals("5")) {
      return MemberMenuChoice.SimpleMemberList;

    } else if (option.equals("6")) {
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
    System.out.println("======> Edit Member <======");
    System.out.println("[1] New name");
    System.out.println("[2] New phone number");
    System.out.println("[3] New email");
    System.out.println("======= [q] to exit =======");
    String option = scan.nextLine();

    if (option.equals("1")) {
      return MemberEditMenuChoice.NewName;

    } else if (option.equals("2")) {
      return MemberEditMenuChoice.NewPhonenmr;

    } else if (option.equals("3")) {
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
    System.out.println("Enter name: ");
    return scan.nextLine();
  }

  public String newPhonenmr(Scanner scan) {
    System.out.println("Enter phone nummber: ");
    return scan.nextLine();
  }

  public String newEmail(Scanner scan) {
    System.out.println("Enter email: ");
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
      System.out.println("ERROR! Already exist or field empty, try again:");
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
      System.out.println("ERROR! Already exist, empty field or missing @, try again:");
      email = newEmail(scan);
      emailExist = choosenMember.checkEmail(email, man.getMemberlst(), scan, choosenMember);
    }
    return email;
  }

  public void chooseAmember() {
    System.out.println("Choose a member: ");
  }

  public void printName(String name) {
    System.out.println("Name: " + name);
  }

  public void memberInfoEmail(String email) {
    System.out.println("Email: " + email);
  }

  public void memberInfoPhoneNumber(String phone) {
    System.out.println("Phone number: " + phone);
  }

  public void memberInfoId(String id) {
    System.out.println("Id: " + id);
  }

  public void memberInfoCash(Integer cash) {
    System.out.println("Cash: " + cash);
  }

  public void indexAndNameAndId(Integer index, String names, String id) {
    System.out.println(index + ". " + names + " - " + id);
  }

  public void errorMessage() {
    System.err.println("ERROR! Invalid input.");
  }

  public ArrayList<Member> getSortedMemberList(Manager manager) {
    return manager.getMemberlstSortedName();
  }
}
