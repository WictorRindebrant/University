package assignment4.boatclub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main function for the boatclub.
 */
public class Main {
  /**
   * In this function we have the main menu.
   * Here we take inputs from the user and calls on functions depending on the inputs.
   */
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in, "utf-8");
    Memberlist member = new Memberlist();
    fileToList(member, scan);

    int options = 10;
    while (options != 0) {
      System.out.println();
      System.out.println("+---------------------------Main menu---------------------------+");
      System.out.println("[1] Create a new member");
      System.out.println("[2] List all members");
      System.out.println("[3] Delete a member");
      System.out.println("[4] Add boat to member");
      System.out.println("[5] Details about a boat");
      System.out.println("[6] Delete a boat");
      System.out.println("+---------------------------0 to exit---------------------------+");
      System.out.println();
      System.out.print("What function do you want to use? ");
      options = Integer.parseInt(scan.nextLine());
      while (options < 0 || options > 6) {
        System.out.println("ERROR: Function " + options + " does not excist");
        System.out.println("What function do you want to use? ");
        options = Integer.parseInt(scan.nextLine());
      }
      if (options == 1) {
        createMember(member, scan);
      } else if (options == 2) {
        allMembers(member);
      } else if (options == 3) {
        deleteMember(member, scan);
      } else if (options == 4) {
        createBoat(member, scan);
      } else if (options == 5) {
        detailBoat(member, scan);
      } else if (options == 6) {
        deleteBoat(member, scan);
      }
    }
    listToFile(member, scan);
    scan.close();
  }

  /**
   * This function lets the user create a new member that will be added to the 
   * Member and the Memberlist class.
   */
  public static void createMember(Memberlist member, Scanner scan) {
    String name = "";
    String email = "";

    System.out.println("Enter your firstname followed by lastname sep with a space: ");
    name = scan.nextLine();

    System.out.println("Do you want to add your email adress? (yes/no): ");
    String question = scan.nextLine();
    if (question.equals("yes")) {
      System.out.println("Enter your email adress: ");
      email = scan.nextLine().toLowerCase();
    }
    String id = "";

    member.addMember(new Member(name, email, id, scan, member));
  }

  /**
   * This function lets the user delete a member depending on the input given.
   */
  public static void deleteMember(Memberlist member, Scanner scan) {
    allMembers(member);
    System.out.println("What member do you want to delete? ( 1 - " + member.getSize() + " )");
    String option = scan.nextLine();
    member.removeMember(Integer.parseInt(option));
  }

  /**
   * This function lets the user list all the members.
   */
  public static void allMembers(Memberlist member) {
    ArrayList<Member> members = member.getMemberList();
    int counter = 1;
    for (Member mem : members) {
      System.out.println(counter + ": Name: " + mem.getName() + "\n   Email: " 
                         + mem.getEmail() + "\n   ID: " + mem.getId());
      counter++;
    }
  }

  /**
   * This function will let the user select an exsisting member to add an new boat to.
   * Depending on the given values from the user it will create that type of boat with
   * the given details about the boat.
   */
  public static void createBoat(Memberlist member, Scanner scan) {
    allMembers(member);
    System.out.println("Who do you want to add a boat to? ( 1 - " + member.getSize() + " )");
    String option1 = scan.nextLine();
    Member theMember = member.getMemberList().get(Integer.parseInt(option1) - 1);

    System.out.println("What type of boat do you want to add? (sailboat / motorboat / motorsailer / canoe): ");
    String type = scan.nextLine();

    if (type.equals("sailboat")) {
      System.out.println("What is the boats depth in meter? ");
      String depthInMeter = scan.nextLine();
      theMember.addBoat(new Sailboat(Integer.parseInt(depthInMeter), scan));
    } else if (type.equals("motorboat")) {
      System.out.println("What is the boats engine power in horse power? ");
      String enginePowerInHorsePower = scan.nextLine();
      theMember.addBoat(new Motorboat(Integer.parseInt(enginePowerInHorsePower), scan));
    } else if (type.equals("motorsailer")) {
      System.out.println("What is the boats depth in meter? ");
      String depthInMeter = scan.nextLine();
      System.out.println("What is the boats engine power in horse power? ");
      String enginePowerInHorsePower = scan.nextLine();
      theMember.addBoat(new Motorsailer(Integer.parseInt(depthInMeter), 
                        Integer.parseInt(enginePowerInHorsePower), scan));
    } else if (type.equals("canoe")) {
      theMember.addBoat(new Canoe(scan));
    }

    System.out.println("What is the name of the boat? ");
    String name = scan.nextLine();

    System.out.println("What is the lenght in meter of the boat? ");
    String lenghtInMeter = scan.nextLine();

    Boat theBoat = theMember.getBoats().get(theMember.getBoats().size() - 1);
    theBoat.setName(name);
    theBoat.setType(type);
    theBoat.setLenght(Integer.parseInt(lenghtInMeter));
  }

  /**
   * To this function lets the user sends in a member and then it prints out all the
   * boats for that given member.
   */
  public static void boatList(Member mem, Scanner scan) {
    ArrayList<Boat> boats = mem.getBoats();
    int counter = 1;
    for (Boat boat : boats) {
      System.out.println(counter + ": " + boat.getName());
      counter++;
    }
  }

  /**
   * Ask the user for what member to view the boat for.
   * Then if the member does own a boat/boats, prints out the list of the boats
   * and lets the user select one of the boats to be deleted.
   */
  public static void deleteBoat(Memberlist member, Scanner scan) {
    allMembers(member);
    System.out.println("What member do you want to see? ( 1 - " + member.getSize() + " )");
    String option1 = scan.nextLine();
    Member theMember = member.getMemberList().get(Integer.parseInt(option1) - 1);

    if (theMember.getBoats().size() == 0) {
      System.out.println("ERROR: This member does not own any boat.");
    } else {
      boatList(theMember, scan);
      System.out.println("What boat do you want to delete? ( 1 - " + theMember.getBoats().size() + " )");
      String option2 = scan.nextLine();
      theMember.removeBoat(Integer.parseInt(option2));
    }
  }

  /**
   * Ask the user for what member to view the boat for.
   * Then if the member does own a boat/boats, prints out the list of the boats
   * and lets the user select one of the boats to see all the details for.
   */
  public static void detailBoat(Memberlist member, Scanner scan) {
    allMembers(member);
    System.out.println("Whos boats do you want to see? ( 1 - " + member.getSize() + " )");
    String option1 = scan.nextLine();
    Member theMember = member.getMemberList().get(Integer.parseInt(option1) - 1);

    if (theMember.getBoats().size() == 0) {
      System.out.println("ERROR: This member does not own any boat.");
    } else {
      boatList(theMember, scan);
      System.out.println("What boat do you want to see the details for? ( 1 - " + theMember.getBoats().size() + " )");
      String option2 = scan.nextLine();
      Boat theBoat = theMember.getBoats().get(Integer.parseInt(option2) - 1);
      String theString = "Name: " + theBoat.getName() + "\nTpye: " + theBoat.getType() + "\nLenght in meter: " 
                          + theBoat.getLenght();
      if (theBoat.getType().equals("sailboat")) {
        System.out.println(theString + "\nDepth in meter: " + theBoat.getDepthInMeter());
      } else if (theBoat.getType().equals("motorboat")) {
        System.out.println(theString + "\nEngine power in horse power" + theBoat.getEnginePowerInHorsePower());
      } else if (theBoat.getType().equals("motorsailer")) {
        System.out.println(theString + "\nDepth in meter: " + theBoat.getDepthInMeter() 
                           + "\nEngine power in horse power" + theBoat.getEnginePowerInHorsePower());
      } else if (theBoat.getType().equals("canoe")) {
        System.out.println(theString);
      }
    }
  }

  /**
   * Opens a file, split the row at the symbol ':'.
   * checking the first position in the new list that was created from the split function.
   * If it's boat then create a boat followed by the rest of the values in that list.
   * If it's member then create a member followed by the rest of the values in that list.
   * If it's a boat then adds it to the last member that was added.
   * Got inpiration from this site: https://www.w3schools.com/java/java_files_read.asp
   */
  public static void fileToList(Memberlist member, Scanner scan) {
    try {
      File file = new File("registry.data");
      Scanner fileScanner = new Scanner(file, "utf-8");

      while (fileScanner.hasNextLine()) {
        String data = fileScanner.nextLine();
        String[] infoInString = data.split(":");

        if (infoInString[0].equals("MEMBER")) {          
          member.addMember(new Member(infoInString[1], infoInString[2], infoInString[3], scan, member));
        }

        if (infoInString[0].equals("BOAT")) {
          Member lastMember = member.getMemberList().get(member.getSize() - 1);
          if (infoInString[2].equals("sailboat")) {
            lastMember.addBoat(new Sailboat(Integer.parseInt(infoInString[4]), scan));
          } else if (infoInString[2].equals("motorboat")) {
            lastMember.addBoat(new Motorboat(Integer.parseInt(infoInString[4]), scan));
          } else if (infoInString[2].equals("motorsailer")) {
            lastMember.addBoat(new Motorsailer(Integer.parseInt(infoInString[4]), 
                                               Integer.parseInt(infoInString[5]), scan));
          } else if (infoInString[2].equals("canoe")) {
            lastMember.addBoat(new Canoe(scan));
          }
          Boat lastBoat = lastMember.getBoats().get(lastMember.getBoats().size() - 1);
          lastBoat.setName(infoInString[1]);
          lastBoat.setType(infoInString[2]);
          lastBoat.setLenght(Integer.parseInt(infoInString[3]));
        }
      }
      fileScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  /**
   * Creating a string with all of the Members and their boats.
   * Then saving that string into the file registry.data.
   * Got inpiration from this site: https://www.w3schools.com/java/java_files_create.asp
   */
  public static void listToFile(Memberlist member, Scanner scan) {
    ArrayList<String> textList = new ArrayList<>();
    ArrayList<Member> memberlist = member.getMemberList();
    for (Member mem : memberlist) {
      textList.add("MEMBER:" + mem.getName() + ":" + mem.getEmail() + ":" + mem.getId());
      for (Boat boa : mem.getBoats()) {
        String text = "BOAT:" + boa.getName() + ":" + boa.getType() + ":" + boa.getLenght();
        if (boa.getType().equals("sailboat")) {
          textList.add(text + ":" + boa.getDepthInMeter());
        } else if (boa.getType().equals("motorboat")) {
          textList.add(text + ":" + boa.getEnginePowerInHorsePower());
        } else if (boa.getType().equals("motorsailer")) {
          textList.add(text + ":" + boa.getDepthInMeter() + ":" + boa.getEnginePowerInHorsePower());
        } else if (boa.getType().equals("canoe")) {
          textList.add(text);
        }
      }
    }
    try {
      try (FileWriter file = new FileWriter("registry.data", StandardCharsets.UTF_8)) {
        for (String i : textList) {
          file.write(i + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}