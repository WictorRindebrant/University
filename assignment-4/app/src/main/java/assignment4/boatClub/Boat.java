package assignment4.boatclub;

/**
 * This is the abstract class for the Boat.
 */
public abstract class Boat {
  private String name;
  private String type;
  private int lenghtInMeter;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }

  public void setLenght(int lenght) {
    this.lenghtInMeter = lenght;
  }

  public int getLenght() {
    return lenghtInMeter;
  }

  public abstract int getDepthInMeter();

  public abstract int getEnginePowerInHorsePower();
}