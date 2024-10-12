package assignment4.boatclub;

import java.util.Scanner;

/**
 * This is the class for creating the boat of the type Motorboat.
 */
public class Motorboat extends Boat {
  private int enginePowerInHorsePower;

  public Motorboat() {}

  public Motorboat(int enginePowerInHorsePower, Scanner scan) {
    this.enginePowerInHorsePower = enginePowerInHorsePower;
  }

  public int getDepthInMeter() {
    return 0;
  }

  public int getEnginePowerInHorsePower() {
    return enginePowerInHorsePower;
  }
}
