package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Store info about the iteam.
 */
public abstract class ItemObject {

  private String name;
  private String description;
  private int costDay;
  private int creationDay;
  private String history;
  private Member ownedBy;
  private Boolean oneTimeOwner = true;

  /**
   * Constructor for item Object.
   *
   * @param name        Name of the object.
   * @param description Short description of the item.
   * @param costDay     Price per day.
   * @param creationDay Day the item was added to the system.
   */

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public ItemObject(String name, String description, int costDay, int creationDay) {
    this.name = name;
    this.description = description;
    this.costDay = costDay;
    this.creationDay = creationDay;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getOwnedBy() {
    return ownedBy;
  }

  /**
   * This solution is so that you can't change the owner of an Item.
   * PS. It's not the best solution but it works.
   *
   * @param ownedBy The member that owns the item.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void setOwnedBy(Member ownedBy) {
    if (oneTimeOwner) {
      this.ownedBy = ownedBy;
      this.oneTimeOwner = false;
    }
  }

  public String getName() {
    return name;
  }

  /**
   * Sets the name of an ItemObject if the name is not the same as
   * another item that the owner owns.
   *
   * @param name   the new name of the item.
   * @param member the member that will own the item.
   * @return true if you can set the name to the name sent in to the fucntion.
   */
  public Boolean setName(String name, Member member) {
    if (member.duplicateItemNameCheck(name)) {
      return false;
    } else {
      this.name = name;
      return true;
    }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCostDay() {
    return costDay;
  }

  public void setCostDay(int costDay) {
    this.costDay = costDay;
  }

  public int getCreationDay() {
    return creationDay;
  }

  public void setCreationDay(int creationDay) {
    this.creationDay = creationDay;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }
}
