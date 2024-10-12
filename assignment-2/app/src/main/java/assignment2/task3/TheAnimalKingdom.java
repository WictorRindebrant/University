package assignment2.task3;

import java.util.ArrayList;

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
    skylark.setWeight(55);
    skylark.setSound("pew pew pew");
    
    Mammal otter = new Mammal("Brown", true);
    otter.setName("Otter");
    otter.setLatinName("Lutrinae");
    otter.setWeight(450);
    otter.setSound("hah");

    Reptile kingCobra = new Reptile("Asia", true);
    kingCobra.setName("King Cobra");
    kingCobra.setLatinName("Ophiophagus hannah");
    kingCobra.setWeight(100);
    kingCobra.setSound("chaa");

    ArrayList<Animal> animals = new ArrayList<Animal>();
    animals.add(skylark);
    animals.add(otter);
    animals.add(kingCobra);

    for (Animal animal : animals) {
      System.out.println("A " + animal.getName() + " " + animal.makeSound() + ": " + animal.getSound());
    }
  }
}
