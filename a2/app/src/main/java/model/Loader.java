package model;

import java.util.Scanner;

/**
 * This class is only to load in some hardcoded object into the program to test
 * with.
 *
 */
public class Loader {
  /**
   * Creates all the objects to test the program with and storing them in the
   * Manager object in different Arraylists.
   *
   * @param man  Manager, where all the objects are stored.
   * @param scan The scanner that handles the input
   */
  public void loadEverything(Manager man, Scanner scan) {
    Member mem1 = hardcodeMember("jonis", "123", "jonis@.se", man);
    Member mem2 = hardcodeMember("wice", "321", "wice@.se", man);
    Member mem3 = hardcodeMember("Ponis", "1234", "Ponis@.se", man);
    Member mem4 = hardcodeMember("Aice", "3215", "Aice@.se", man);
    Member mem5 = hardcodeMember("Oonis", "1236", "Oonis@.se", man);
    Member mem6 = hardcodeMember("Zice", "3217", "Zice@.se", man);

    man.addMember(mem1);
    man.addMember(mem2);
    man.addMember(mem3);
    man.addMember(mem4);
    man.addMember(mem5);
    man.addMember(mem6);

    ItemObject item1 = new Tool("hammare", "It hits really hard", 10, 0);
    ItemObject item2 = new Other("spik", "den slor fett hort", 10, 0);
    mem1.addItem(item2);
    mem2.addItem(item1);
    man.addItem(item1);
    man.addItem(item2);

    Contract con1 = new Contract(0, 2, mem1, item1);
    man.addContract(con1);
  }

  /**
   * Creates a Member and fills it with Hardcoded values.
   *
   * @param name     The Members name.
   * @param phonenmr The Members Phone number.
   * @param email    The Members email.
   * @param man      Manager class that handles all the lists.
   * @return Returns a Member.
   */
  private Member hardcodeMember(String name, String phonenmr, String email, Manager man) {
    Member mem = new Member();
    mem.setName(name);
    mem.setPhonenmr(phonenmr);
    mem.setEmail(email);
    mem.setCreationDay(0);
    mem.setId(man.getMemberlst());
    return mem;
  }
}