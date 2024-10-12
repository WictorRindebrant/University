package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is just an example contoller class. You should remove it.
 */

public class Member {
  private Random rand = new Random();
  private String name;
  private String phonenmr;
  private String email;
  private String id;
  private int creationDay;
  private int cash;
  private ArrayList<ItemObject> ownItems = new ArrayList<>();

  /**
   * Scan the member list to finde duplicates of numbers.
   * If no duplicates exist store the new number.
   *
   * @param phonenmr  New phone numbers.
   * @param memberlst List with all members.
   * @param scan      Input scan.
   */
  public boolean checkPhonenmr(String phonenmr, ArrayList<Member> memberlst, Scanner scan, Member mem) {
    Boolean nmrExist = false;
    for (Member i : memberlst) {
      if (i.getPhonenmr().equals(phonenmr)) {
        nmrExist = true;
      }
    }
    if (nmrExist || phonenmr.isEmpty()) {
      return true;
    } else {
      mem.setPhonenmr(phonenmr);
      return false;
    }
  }

  public void setPhonenmr(String nmr) {
    this.phonenmr = nmr;
  }

  /**
   * Scan the member list to finde duplicates of emails.
   * If no duplicates exist store the new email.
   *
   * @param email     New email
   * @param memberlst List with all members.
   * @param scan      Input scan.
   */
  public Boolean checkEmail(String email, ArrayList<Member> memberlst, Scanner scan, Member mem) {
    Boolean emailExist = false;
    for (Member i : memberlst) {
      if (i.getEmail().equals(email)) {
        emailExist = true;
      }
    }
    if (emailExist || email.isEmpty() || !email.contains("@")) {
      return true;
    } else {
      return false;
    }
  }

  public void setEmail(String mail) {
    this.email = mail;
  }

  /**
   * Generate new id.
   *
   * @return new ID
   */
  public String generateId() {
    String alphaNumeric = "abcdefghijklmnopqrstuvwxyz0123456789";
    String newId = "";

    for (int i = 0; i < 6; i++) {
      char am = alphaNumeric.charAt(rand.nextInt(36));
      newId += am;
    }

    return newId;
  }

  /**
   * Generate new IDs for members.
   * If no duplicates exist store the ID.
   *
   * @param memberlst list with members
   */
  public void setId(ArrayList<Member> memberlst) {
    String newId = generateId();
    Boolean idExist = false;
    for (Member i : memberlst) {
      if (i.getId().equals(newId)) {
        idExist = true;
      }
    }
    if (idExist) {
      setId(memberlst);
    } else {
      this.id = newId;
    }
  }

  /**
   * Copy the members items.
   *
   * @return List of items the members ownes.
   */

  public ArrayList<ItemObject> getOwnItems() {
    ArrayList<ItemObject> copyOwnItems = new ArrayList<>();
    for (ItemObject i : this.ownItems) {
      copyOwnItems.add(i);
    }
    return copyOwnItems;
  }

  public int getCreationDay() {
    return creationDay;
  }

  public void setCreationDay(int creationDay) {
    this.creationDay = creationDay;
  }

  public void removeItem(int removeItem) {
    this.ownItems.remove(removeItem);
  }

  /**
   * Adds an item to a Member if the name is not the same as another
   * Item that the chosen Member is owning.
   *
   * @param newItem the new item that will be added.
   * @return return true if the item was added.
   */
  public Boolean addItem(ItemObject newItem) {
    if (duplicateItemNameCheck(newItem.getName())) {
      return false;
    } else {
      ownItems.add(newItem);
      newItem.setOwnedBy(this);
      addCash(100);
      return true;
    }
  }

  public void addCash(int cash) {
    this.cash += cash;
  }

  public int getCash() {
    return cash;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Checks if the member has an item with the name that the user send in to the
   * function.
   *
   * @param itemName The item name to check duplications for.
   * @return true if item with that name is found. returns false if item with that
   *         name is not found.
   */
  public boolean duplicateItemNameCheck(String itemName) {
    for (ItemObject i : ownItems) {
      if (i.getName().equals(itemName)) {
        return true;
      }
    }
    return false;
  }

  public String getPhonenmr() {
    return phonenmr;
  }

  public String getEmail() {
    return email;
  }

  public String getId() {
    return id;
  }
}
