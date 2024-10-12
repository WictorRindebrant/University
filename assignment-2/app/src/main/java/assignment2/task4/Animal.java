package assignment2.task4;

/**
 * This is the abstract class for the Animal.
 */
public abstract class Animal implements Comparable<Animal> {
  private String name;
  private String latinName;
  private double weight;
  private String sound;
  
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

  public abstract String attribute();

  /**
   * Compares LatinName with the LatinName the user is sending in to the function to se 
   * if it's samller or bigger to be able to place it right correct in a list with LatinNames.
   */
  public int compareTo(Animal theAnimal) {
    int compNumber = 0;
    if (this.getLatinName().compareTo(theAnimal.getLatinName()) > 0) {
      compNumber = 1;
    } else {
      compNumber = -1;
    }
    return compNumber;
  }
}
