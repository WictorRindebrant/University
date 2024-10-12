package assignment2.task4;

/**
 * This is the class for the Mammal that aslo extends the Animal class.
 */
public class Mammal extends Animal {
  private String furColor;
  private boolean hasWinterFur;

  public Mammal() {}

  public Mammal(String furColor, boolean hasWinterFur) {
    this.furColor = furColor;
    this.hasWinterFur = hasWinterFur;
  }

  public String getFurColor() {
    return furColor;
  }

  public void setFurColor(String furColor) {
    this.furColor = furColor;
  }

  public boolean isHasWinterFur() {
    return hasWinterFur;
  }

  public void setHasWinterFur(boolean hasWinterFur) {
    this.hasWinterFur = hasWinterFur;
  }

  public String makeSound() {
    return "says";
  }

  public String attribute() {
    return "has a fur that is " + furColor;
  }
}
