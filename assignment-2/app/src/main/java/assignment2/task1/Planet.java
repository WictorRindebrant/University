package assignment2.task1;

/**
 * This is the where we save the varibles for the planet class.
 */
public class Planet {
  private String name;
  private int position;
  private int noOfMoons;
  private int aphelion;
  private int perihelion;

  public Planet(){}

  /**
   * This is the get and set for the planet.
   */
  public Planet(String name, int position, int noOfMoons, int aphelion, int perihelion) {
    this.name = name;
    this.position = position;
    this.noOfMoons = noOfMoons;
    this.aphelion = aphelion;
    this.perihelion = perihelion;
  }

  /**
   * gets the name if 2 or longer. Else sets it to "Unkown Name" and returns.
   */
  public String getName() {
    if (name.length() <= 2) {
      System.out.print("ERROR: The name of the planet was to short. ");
      System.out.println("The planet name was set to 'Unknown Name' and returned.");
      this.name = "Unknown Name";
    }
    return this.name;
  }

  /**
   * sets the name if 2 or longer. Else sets it to "Unkown Name".
   */
  public void setName(String name) {
    if (name.length() <= 2) {
      System.out.print("ERROR: The name of the planet was to short. ");
      System.out.println("The planet name Was set to 'Unknown Name'");
      this.name = "Unknown Name";
    } else {
      this.name = name;
    }
  }

  /**
   * gets the position if greater or equal to 0. Else sets it to "0" and returns.
   */
  public int getPosition() {
    if (position < 0) {
      System.out.print("ERROR: The position of the planet cant be negative. ");
      System.out.println("The planet position was set to '0' and returned.");
      this.position = 0;
    }
    return this.position;
  }

  /**
   * sets the position if greater or equal to 0. Else sets it to "0".
   */
  public void setPosition(int position) {
    if (position < 0) {
      System.out.print("ERROR: The position of the planet cant be negative. ");
      System.out.println("The planet position was set to '0'");
      this.position = 0;
    } else {
      this.position = position;
    }
  }

  /**
   * gets the noOfMoons if greater or equal to 0. Else sets it to "0" and returns.
   */
  public int getNoOfMoons() {
    if (noOfMoons < 0) {
      System.out.print("ERROR: The noOfMoons of the planet cant be negative. ");
      System.out.println("The planets noOfMoons was set to '0' then returned.");
      this.noOfMoons = 0;
    } 
    return this.noOfMoons;
  }

  /**
   * sets the noOfMoons if greater or equal to 0. Else sets it to "0".
   */
  public void setNoOfMoons(int noOfMoons) {
    if (noOfMoons < 0) {
      System.out.print("ERROR: The noOfMoons of the planet cant be negative. ");
      System.out.println("The planets noOfMoons was set to '0'");
      this.noOfMoons = 0;
    } else {
      this.noOfMoons = noOfMoons;
    }
  }

  /**
   * gets the Aphelion distance if greater than 0. Else sets it to "1" and returns.
   */
  public int getAphelion() {
    if (aphelion <= 0) {
      System.out.print("ERROR: The Aphelion distance from the planet cant be negative or '0'. ");
      System.out.println("The Aphelion distance from planet was set to '1' then returned.");
      this.aphelion = 1;
    } 
    return this.aphelion;
  }

  /**
   * sets the Aphelion distance if greater than 0. Else sets it to "1".
   */
  public void setAphelion(int aphelion) {
    if (aphelion <= 0) {
      System.out.print("ERROR: The Aphelion distance from the planet cant be negative or '0'. ");
      System.out.println("The Aphelion distance from planet was set to '1'.");
      this.aphelion = 1;
    } else {
      this.aphelion = aphelion;
    }
  }

  /**
   * gets the Perihelion distance if greater than 0. Else sets it to "1" and returns.
   */
  public int getPerihelion() {
    if (perihelion <= 0) {
      System.out.print("ERROR: The Perihelion distance from the planet cant be negative or '0'. ");
      System.out.println("The Perihelion distance from planet was set to '1'.");
      this.perihelion = 1;
    } 
    return this.perihelion;
  }

  /**
   * gets the Perihelion distance if greater than 0. Else sets it to "1".
   */
  public void setPerihelion(int perihelion) {
    if (perihelion <= 0) {
      System.out.print("ERROR: The Perihelion distance from the planet cant be negative or '0'. ");
      System.out.println("The Perihelion distance from planet was set to '1'.");
      this.perihelion = 1;
    } else {
      this.perihelion = perihelion;
    }
  }
}
