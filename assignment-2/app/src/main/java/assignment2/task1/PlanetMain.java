package assignment2.task1;

/**
 * This is the class for planet main.
 */
public class PlanetMain {
  /**
   * This is the main function for planets.
   */
  public static void main(String[] args) {
    Planet earth = new Planet("Earth", 3, 1, 152097701, 147098074);
    Planet mars = new Planet("Mars", 4, 2, 249209300, 206669000);

    thePlanet(earth);
    thePlanet(mars);
  }

  /**
   * This is the print function for planet main.
   */
  private static void thePlanet(Planet planet) {
    System.out.println(planet.getName() + ":");
    System.out.println("  Position: " + planet.getPosition());
    System.out.println("  Moons: " + planet.getNoOfMoons());
    System.out.println("  Aphelion: " + planet.getAphelion() + " km");
    System.out.println("  Perihelion: " + planet.getPerihelion() + " km");
  }
}
