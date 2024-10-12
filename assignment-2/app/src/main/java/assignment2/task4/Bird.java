package assignment2.task4;

/**
 * This is the class for the Bird that aslo extends the Animal class.
 */
public class Bird extends Animal {
  private boolean isMigrant;
  private boolean canFly;
  private String nestType;

  public Bird() {}

  /**
   * This is where we save and change all the varibales for the Bird class.
   */
  public Bird(boolean isMigrant, boolean canFly, String nestType) {
    this.isMigrant = isMigrant;
    this.canFly = canFly;
    this.nestType = nestType;
  }
  
  public boolean isMigrant() {
    return isMigrant;
  }

  public void setMigrant(boolean isMigrant) {
    this.isMigrant = isMigrant;
  }

  public boolean isCanFly() {
    return canFly;
  }

  public void setCanFly(boolean canFly) {
    this.canFly = canFly;
  }

  public String getNestType() {
    return nestType;
  }

  public void setNestType(String nestType) {
    this.nestType = nestType;
  }

  public String makeSound() {
    return "tweets";
  }

  public String attribute() {
    return "puts its egg in/on a " + nestType;
  }
}
