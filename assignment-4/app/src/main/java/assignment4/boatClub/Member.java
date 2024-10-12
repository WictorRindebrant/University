package assignment4.boatclub;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the class Member.
 */
public class Member {
  private String name;
  private String email;
  private String id;
  private ArrayList<Boat> boats = new ArrayList<>();
  Random rand = new Random();

  public Member() {}

  /**
   * This is the constructor for the Member that sends all value
   * in to the functions to check the values before setting them.
   */
  public Member(String name, String email, String id, Scanner scan, Memberlist member) {
    setName(name, scan);
    setEmail(email, scan, member);
    setId(id, member);
  }

  /**
   * Checking that the name contains atleast one space and only alphabetic characters.
   */
  public void setName(String name, Scanner scan) {
    while (name.matches("[A-Za-z ]*") == false || name.contains(" ") == false) {
      System.out.println("ERROR: the name was not correct for: " + name);
      System.out.println("Please enter your first name followed by lastname sep with space: ");
      name = scan.nextLine();
    }
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Checking that the email contains atleast one '@' and one '.' and not one space.
   * Also check the email for other duplicates from the other members in the Memberlist.
   */
  public void setEmail(String email, Scanner scan, Memberlist member) {
    ArrayList<Member> members = member.getMemberList();
    String emails = "";
    for (Member mem : members) {
      emails += mem.getEmail() + "";
    }
    if (email.equals("")) {
      ;
    } else {
      email = email.toLowerCase();
      while (email.contains("@") == false || email.contains(".") == false 
             || email.contains(" ") == true || emails.contains(email) == true) {
        System.out.println("ERROR: the email was not correct for: " + email);
        System.out.println("Please enter your email again(Needs to contain '@' and '.' and not ' '): ");
        email = scan.nextLine().toLowerCase();
      }
    }
    this.email = email.toLowerCase();
  }

  public String getEmail() {
    return this.email;
  }

  /**
   * Creating an random generated id code with the numbers between 1-9 and letter between a-z.
   * Also check the other members if they have the same id and in that case generates a new id code.
   */
  public void setId(String id, Memberlist member) {
    ArrayList<Member> members = member.getMemberList();
    String ids = "";
    for (Member mem : members) {
      ids += mem.getId() + "";
    }

    while (id == "" || ids.contains(id)) {
      String possibilty = "123456789abcdefghijklmnopqrstuvwxyz";
      String[] arraypossibilty = possibilty.split("");
      for (int i = 0; i < 6; i++) {
        id += arraypossibilty[rand.nextInt(arraypossibilty.length)];
      }
    }
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void addBoat(Boat boat) {
    boats.add(boat);
  }

  public void removeBoat(int index) {
    boats.remove(boats.get(index - 1));
  }

  /**
   * Creating a copy of the ArrayList boats and returning the copy.
   */
  public ArrayList<Boat> getBoats() {
    ArrayList<Boat> boatsCopy = new ArrayList<>();
    for (Boat i : boats) {
      boatsCopy.add(i);
    }
    return boatsCopy;
  }
}