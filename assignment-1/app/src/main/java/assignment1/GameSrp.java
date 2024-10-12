package assignment1;

import java.util.Random;
import java.util.Scanner;

/**
* This is the class for Task5.
*/
public class GameSrp {
  Random random = new Random();

  /**
  * This program lets the user type in a number between 0 and 3.
  * 1 - 3 is for Scissor, rock and paper. Then a number is randomly generatad for the computer.
  * The if statements compares the values and return if the user wins or loses over the computer.
  * if the user types in 0 the program will end after it prints out all the saved values as result.
  */
  public void gameSrp(Scanner input) {
    int intInput = 1;
    int you = 0;
    int comp = 0;
    int draw = 0;
    int compHand = 0;

    while (intInput == 1 || intInput == 2 || intInput == 3 || intInput == 0) {
      System.out.print("Scissor (1), rock (2), paper (3) or 0 to quit: ");
      intInput = input.nextInt();
      compHand = randomNumber();

      if (intInput == compHand) {
        draw++;
        System.out.println("It's a draw!");
      } else if (intInput == 1) {
        if (compHand == 2) {
          comp++;
          System.out.println("You lost, computer had rock!");
        } else if (compHand == 3) {
          you++;
          System.out.println("You won, computer had paper!");
        }
      } else if (intInput == 2) {
        if (compHand == 1) {
          you++;
          System.out.println("You won, computer had scissor!");
        } else if (compHand == 3) {
          comp++;
          System.out.println("You lost, computer had paper!");
        }
      } else if (intInput == 3) {
        if (compHand == 1) {
          comp++;
          System.out.println("You lost, computer had scissor!");
        } else if (compHand == 2) {
          you++;
          System.out.println("You won, computer had rock!");
        }
      }

      if (intInput == 0) {
        System.out.println("Score: " + you + " (you) " + comp + " (computer) " + draw + " (draw).");
        break;
      }
    }
  }
  
  /**
  * This function returns an random generated int between 1 and 3.
  */
  private int randomNumber() {
    return random.nextInt(3) + 1;
  }
}
