package assignment2.task4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the main class for the animal kingdom.
 */
public class TheAnimalKingdom {
  /**
   * This is the main class for the animal kingdom. It's the starting point for
   * all the files in the directory task3.
   */
  public static void main(String[] args) {
    Bird skylark = new Bird(true, true, "shallow depression in the ground");
    skylark.setName("Skylark");
    skylark.setLatinName("Alauda arvensis");
    
    Mammal otter = new Mammal("Brown", true);
    otter.setName("Otter");
    otter.setLatinName("Lutrinae");

    Reptile kingCobra = new Reptile("Asia", true);
    kingCobra.setName("King Cobra");
    kingCobra.setLatinName("Ophiophagus hannah");

    Bird parrot = new Bird(false, true, "nest of twigs and sticks");
    parrot.setName("Parrot");
    parrot.setLatinName("Psittacines");

    Mammal chipmunk = new Mammal("brown with white spripes", true);
    chipmunk.setName("Chipmunks");
    chipmunk.setLatinName("Tamias");

    Reptile chameleon = new Reptile("in the forest", false);
    chameleon.setName("Chameleon");
    chameleon.setLatinName("Chamaeleonidae");

    ArrayList<Animal> animals = new ArrayList<Animal>();
    animals.add(skylark);
    animals.add(otter);
    animals.add(kingCobra);
    animals.add(parrot);
    animals.add(chipmunk);
    animals.add(chameleon);

    Collections.sort(animals);

    for (Animal c : animals) {
      if (c instanceof Bird) {
        System.out.println(c.getLatinName() + " " + c.attribute());
      } else if (c instanceof Mammal) {
        System.out.println(c.getLatinName() + " " + c.attribute());
      } else if (c instanceof Reptile) {
        System.out.println(c.getLatinName() + " " + c.attribute());
      }
    }
  }
}
