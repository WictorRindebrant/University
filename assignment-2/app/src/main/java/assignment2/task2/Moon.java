package assignment2.task2;

/**
 * This is the where we save the varibles for moon class.
 */
public class Moon {
  String name;
  int kmSize;

  public Moon() {}

  /**
   * This is the get and set for the moon.
   */
  public Moon(String name, int kmSize) {
    this.name = name;
    this.kmSize = kmSize;
  }


  public String getName() {
    return this.name;
  }


  public int getSizelnKm() {
    return this.kmSize;
  }
}
