package assignment4.boatclub;

import java.util.Scanner;

/**
 * This is the class for creating the boat of the type Motorsailer.
 */
public class Motorsailer extends Boat {
  private int depthInMeter;
  private int enginePowerInHorsePower;

  public Motorsailer() {}

  /**
   * The constructor for the Motorsailer class.
   */
  public Motorsailer(int depthInMeter, int enginePowerInHorsePower, Scanner scan) {
    this.depthInMeter = depthInMeter;
    this.enginePowerInHorsePower = enginePowerInHorsePower;
  }

  public int getEnginePower() {
    return enginePowerInHorsePower;
  }

  public int getDepth() {
    return depthInMeter;
  }

  public int getDepthInMeter() {
    return depthInMeter;
  }

  public int getEnginePowerInHorsePower() {
    return enginePowerInHorsePower;
  }
}
