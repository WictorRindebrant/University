package view;

/**
 * The Interface for the MemberView Classes.
 */
public interface MemberInterfaceView {

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Member Menu.
   */
  public static enum MemberMenuChoice {
    AddMember,
    MemberInfo,
    DeleteMember,
    ChangeMemberInfo,
    SimpleMemberList,
    VerboseMemberList,
    Quit
  }

  /**
   * Prints the Member Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MemberMenuChoice showMemberMenu(java.util.Scanner scan);

  /**
   * Making the switch case look nicer in the Menu.java class.
   * This switch case is for the Edit Member Menu.
   */
  public static enum MemberEditMenuChoice {
    NewName,
    NewPhonenmr,
    NewEmail,
    Quit
  }

  /**
   * Prints the Edit Member Menu options.
   *
   * @param scan Handles all inputs.
   * @return returns an value depending on what function the user wants to enter.
   */
  public MemberEditMenuChoice showMemberEditMenu(java.util.Scanner scan);

  /**
   * Creates a new Member depending on what is sent in to the function and some
   * inputs.
   * Then returns that Member.
   *
   * @param scan Handles the inputs.
   * @param man  Handles the inputs.
   * @return Returns the Member.
   */
  public model.Member newMember(java.util.Scanner scan, model.Manager man);

  public String newName(java.util.Scanner scan);

  public String newPhonenmr(java.util.Scanner scan);

  public String newEmail(java.util.Scanner scan);

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
  public String phoneCheck(java.util.Scanner scan, model.Member choosenMember, model.Manager man, String phonenmr);

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
  public String emailCheck(java.util.Scanner scan, model.Member choosenMember, model.Manager man, String email);

  public void chooseAmember();

  public void printName(String name);

  public void memberInfoEmail(String email);

  public void memberInfoPhoneNumber(String phone);

  public void memberInfoId(String id);

  public void memberInfoCash(Integer cash);

  public void indexAndNameAndId(Integer index, String names, String id);

  public java.util.ArrayList<model.Member> getSortedMemberList(model.Manager manager);

  public void errorMessage();

}
