package assignment2.task3;

/**
 * This is the class for the Reptile that aslo extends the Animal class.
 */
public class Reptile extends Animal {
  private String habitat;
  private boolean isPoisonous;

  public Reptile() {}

  public Reptile(String habitat, boolean isPoisonous) {
    this.habitat = habitat;
    this.isPoisonous = isPoisonous;
  }

  public String getHabitat() {
    return habitat;
  }

  public void setHabitat(String habitat) {
    this.habitat = habitat;
  }

  public boolean isPoisonous() {
    return isPoisonous;
  }

  public void setPoisonous(boolean isPoisonous) {
    this.isPoisonous = isPoisonous;
  }

  public String makeSound() {
    return "hizzes";
  }
}
