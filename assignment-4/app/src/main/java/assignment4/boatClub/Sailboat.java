package assignment4.boatclub;

import java.util.Scanner;

/**
 * This is the class for creating the boat of the type Sailboat.
 */
public class Sailboat extends Boat {
  private int depthInMeter;

  public Sailboat() {}

  public Sailboat(int depthInMeter, Scanner scan) {
    this.depthInMeter = depthInMeter;
  }

  public int getDepth() {
    return depthInMeter;
  }

  public int getDepthInMeter() {
    return depthInMeter;
  }

  public int getEnginePowerInHorsePower() {
    return 0;
  }
}