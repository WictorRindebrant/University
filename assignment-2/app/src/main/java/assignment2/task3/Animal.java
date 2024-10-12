package assignment2.task3;

/**
 * This is the abstract class for the Animal.
 */
public abstract class Animal {
  private String name;
  private String latinName;
  private double weight;
  protected String sound;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLatinName() {
    return latinName;
  }

  public void setLatinName(String latinName) {
    this.latinName = latinName;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getSound() {
    return sound;
  }

  public void setSound(String sound) {
    this.sound = sound;
  }

  public abstract String makeSound();
}
