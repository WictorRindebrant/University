package model;

import java.util.ArrayList;

/**
 * Store all Members, items and contracts.
 */

public class Manager {
  private int toDay;
  private ArrayList<Member> memberlst = new ArrayList<>();
  private ArrayList<Contract> contractlst = new ArrayList<>();
  private ArrayList<ItemObject> itemlst = new ArrayList<>();

  public int getToDay() {
    return toDay;
  }

  public void setToDay(int toDay) {
    this.toDay = toDay;
  }

  /**
   * Creates and returns a deep copy of the Item list.
   *
   * @return returns a deep copy of the item lst.
   */
  public ArrayList<ItemObject> getItemlst() {
    ArrayList<ItemObject> copyItemlst = new ArrayList<>();
    for (ItemObject i : this.itemlst) {
      copyItemlst.add(i);
    }
    return copyItemlst;
  }

  /**
   * Creates and returns a deep copy of the Member list.
   *
   * @return returns a deep copy of the Member lst.
   */
  public ArrayList<Member> getMemberlst() {
    ArrayList<Member> copyMemberlst = new ArrayList<>();
    for (Member i : this.memberlst) {
      copyMemberlst.add(i);
    }
    return copyMemberlst;
  }

  /**
   * Creates and returns a deep copy of the Member list sorted by the member ID.
   *
   * @return returns a deep copy of the Member lst.
   */
  public ArrayList<Member> getMemberlstSortedId() {
    ArrayList<Member> copyMemberlst = new ArrayList<>();
    for (Member i : this.memberlst) {
      copyMemberlst.add(i);
    }
    ArrayList<Member> sortedMemberlstName = new ArrayList<>();
    while (copyMemberlst.size() != 0) {
      Member nextInLineMember = copyMemberlst.get(0);
      for (Member i : copyMemberlst) {
        int compare = nextInLineMember.getId().toLowerCase().compareTo(i.getId().toLowerCase());
        if (compare > 0) {
          nextInLineMember = i;
        }
      }
      sortedMemberlstName.add(nextInLineMember);
      copyMemberlst.remove(nextInLineMember);
    }
    return sortedMemberlstName;
  }

  /**
   * Creates and returns a deep copy of the Member list sorted by the member Name.
   *
   * @return returns a deep copy of the Member lst.
   */
  public ArrayList<Member> getMemberlstSortedName() {
    ArrayList<Member> copyMemberlst = new ArrayList<>();
    for (Member i : this.memberlst) {
      copyMemberlst.add(i);
    }
    ArrayList<Member> sortedMemberlstName = new ArrayList<>();
    while (copyMemberlst.size() != 0) {
      Member nextInLineMember = copyMemberlst.get(0);
      for (Member i : copyMemberlst) {
        int compare = nextInLineMember.getName().toLowerCase().compareTo(i.getName().toLowerCase());
        if (compare > 0) {
          nextInLineMember = i;
        }
      }
      sortedMemberlstName.add(nextInLineMember);
      copyMemberlst.remove(nextInLineMember);
    }
    return sortedMemberlstName;
  }

  /**
   * Creates and returns a deep copy of the Contract list.
   *
   * @return returns a deep copy of the Member lst.
   */
  public ArrayList<Contract> getContractlst() {
    ArrayList<Contract> copyContractlst = new ArrayList<>();
    for (Contract i : this.contractlst) {
      copyContractlst.add(i);
    }
    return copyContractlst;
  }

  public void addItem(ItemObject item) {
    itemlst.add(item);
  }

  public void addMember(Member member) {
    memberlst.add(member);
  }

  public void addContract(Contract contract) {
    contractlst.add(contract);
  }

  public void removeItem(int index) {
    itemlst.remove(index);
  }

  public void removeMember(Member member) {
    memberlst.remove(member);
  }

  public void removeContract(int index) {
    contractlst.remove(index);
  }

  /**
   * Checking the ItemObject for overlapping contracts.
   *
   * @param startDay Contracts start day.
   * @param endDay   Contracts end day.
   * @param item     Contracts item object.
   * @return true if found overlapp and else false.
   */
  public boolean contractOverlap(int startDay, int endDay, ItemObject item) {
    Boolean overlap = false;
    for (Contract c : getContractlst()) {
      if (c.getItem().equals(item)) {
        if (c.getEndDay() >= startDay && c.getStartDay() <= startDay) {
          overlap = true;
        }
        if (c.getEndDay() >= endDay && c.getStartDay() <= endDay) {
          overlap = true;
        }
      }
    }
    if (startDay < getToDay()) {
      overlap = true;
    }
    if (startDay > endDay) {
      overlap = true;

    }
    return overlap;
  }
}
