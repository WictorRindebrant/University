package assignment4.boatclub;

import java.util.ArrayList;

/**
 * This is the class for storing all the classes Member in one class/list.
 */
public class Memberlist {
  private ArrayList<Member> memberList = new ArrayList<>();

  public void addMember(Member member) {
    memberList.add(member);
  }

  public void removeMember(int index) {
    memberList.remove(memberList.get(index - 1));
  }

  /**
   * Creating a copy of the memberList and returning the copy.
   */
  public ArrayList<Member> getMemberList() {
    ArrayList<Member> theList = new ArrayList<>();
    for (Member memb : memberList) {
      theList.add(memb);
    }
    return theList;
  }

  public int getSize() {
    return memberList.size();
  }

}
