package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Store all the data about Contracts.
 */

public class Contract {

  private Integer startDay;
  private Integer endDay;
  private Integer priceDay;
  private Member lender;
  private Member receiver;
  private ItemObject item;

  /**
   * Constructor for Contract object.
   *
   * @param startDay Start day of the contract.
   * @param endDay   End day of the contract.
   * @param receiver Member that lends the item.
   * @param item     The item the contract is about.
   */

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Contract(Integer startDay, Integer endDay, Member receiver, ItemObject item) {
    this.startDay = startDay;
    this.endDay = endDay;
    this.priceDay = item.getCostDay();
    this.lender = item.getOwnedBy();
    this.receiver = receiver;
    this.item = item;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getLender() {
    return lender;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void setLender(Member lender) {
    this.lender = lender;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public Member getReceiver() {
    return receiver;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void setReceiver(Member receiver) {
    this.receiver = receiver;
  }

  public Integer getStartDay() {
    return startDay;
  }

  public void setStartDay(Integer startDay) {
    this.startDay = startDay;
  }

  public Integer getEndDay() {
    return endDay;
  }

  public void setEndDay(Integer endDay) {
    this.endDay = endDay;
  }

  public Integer getPriceDay() {
    return priceDay;
  }

  public void setPriceDay(Integer priceDay) {
    this.priceDay = priceDay;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public ItemObject getItem() {
    return item;
  }

  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We want this.")
  public void setItem(ItemObject item) {
    this.item = item;
  }

}
