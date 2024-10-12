package assignment2.task2;

import java.util.ArrayList;

/**
 * This is the class for planetMoon main.
 */
public class PlanetMoonMain {
  /**
   * This is the main for planetMoon main.
   */
  public static void main(String[] args) {
    Planet earth = new Planet("Earth", 3, 1, 152097701, 147098074);
    ArrayList<Moon> moonsEarth = new ArrayList<Moon>();
    moonsEarth.add(new Moon("The Moon", 3474));
    for (int i = 0; i < moonsEarth.size(); i++) {
      earth.addMoon(moonsEarth.get(i));
    }

    Planet mars = new Planet("Mars", 4, 2, 249209300, 206669000);
    ArrayList<Moon> moonsMars = new ArrayList<Moon>();
    moonsMars.add(new Moon("Phobos", 22));
    moonsMars.add(new Moon("Deimos", 12));
    for (int i = 0; i < moonsMars.size(); i++) {
      mars.addMoon(moonsMars.get(i));
    }

    thePlanet(mars);
    System.out.println();
    thePlanet(earth);
  }

  /**
   * This is the print function for planetMoon main.
   */
  private static void thePlanet(Planet planet) {
    if (planet.getNoOfMoons() > 0) {
      Moon[] allMoons = planet.getMoons();

      System.out.println("The planet is called " + planet.getName() + " and has the following moons:");
      
      for (int i = 0; i < planet.getNoOfMoons(); i++) {
        Moon currentMoon = allMoons[i];
        int counterMoon = i + 1;
        System.out.print("  Moon " + counterMoon + " is called " + currentMoon.name);
        System.out.println(" (" + currentMoon.kmSize + "km)");
      }
    } 
  }
}
