package assignment1;

import java.util.Scanner;

/**
* This is the class for Task4.
*/
public class Diamonds {
  /**
  * A diamond is printed with the help of one while loop with two while loops inside and some
  * if statements. The two while loops is for printing spaces and stars and the if statements
  * is used for changing how manny stars and spaces ther should be on every line.
  * when the two while loops are done it will jumping down one line in the print.
  */
  public void diamonds(Scanner input) {
    System.out.print("Give a positive number: ");
    int intInput = input.nextInt();
    while (intInput >= 1) {
      int counter = 0;
      int countTo = (intInput * 2) - 1;
      int space = 0;
      int spaceTo = intInput - 2;
      int star = 0;
      int starTo = 0;
      int spaces = 0;
      int stars = 0;

      while (counter != countTo) {
        if (counter < intInput - 1) {
          spaces = - 1;
          stars = + 2;
        }
        if (counter > intInput - 2) {

          spaces = + 1;
          stars = - 2;
        }
        while (space <= spaceTo) {
          System.out.print(" ");
          space = space + 1;
        }
        while (star != starTo) {
          System.out.print("*");
          star = star + 1;
        }
        System.out.println("*");
        spaceTo = spaceTo + spaces;
        starTo = starTo + stars;
        star = 0;
        space = 0;
        counter = counter + 1;
      }
      System.out.print("Give a positive number: ");
      intInput = input.nextInt();
    }
  }
}
