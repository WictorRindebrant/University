package assignment1;

import java.util.Random;
import java.util.Scanner;

/**
* This is the class for Task2.
*/
public class Nine {
  Random random = new Random();

  /**
  * This is the main function for Task2.
  */
  public void hitNine(Scanner input) {
    int firstNumber = 0;
    int secNumber = 0;
    int yourTot = 0;
    int compFirstNumber = 0;
    int compSecNumber = 0;
    int compTot = 0;

    // Lets the user enter an string Input, if it's Y then it will generate an random int 
    // between 1 and 6 then ask if the user wants another random number by typing Y again.
    System.out.println("Playing a game");
    System.out.println("=================");
    System.out.println("");
    System.out.print("Ready to play? (Y/N) ");
    String strInput1 = input.next();

    if (strInput1.equals("Y")) {
      firstNumber = randomNumber();
      System.out.println("You rolled " + firstNumber);

      System.out.print("Would you like to roll again? (Y/N) ");
      String strInput2 = input.next();

      if (strInput2.equals("Y")) {
        secNumber = randomNumber();
        yourTot = firstNumber + secNumber;
        System.out.println("You rolled " + secNumber + " and in total you have " + yourTot);
      }

      // Creating a random number between 1 and 6 then checking that random number if it's smaller
      // or equal to 4. If that's true then generate a new number and add it to 4.
      compFirstNumber = randomNumber();
      System.out.println("The computer rolled " + compFirstNumber);

      if (compFirstNumber <= 4) {
        compSecNumber = randomNumber();
        compTot = compFirstNumber + compSecNumber;
        System.out.print("The computer rolls again and gets ");
        System.out.println(compSecNumber + " in total " + compTot);
      }
      
      //Sending in the two values created above to see who wins and then prints that.
      compTot = compFirstNumber + compSecNumber;
      String winner = whoWillWin(yourTot, compTot);
      System.out.println(winner);
    }
  }

  /**
  * This function returns an random generated int between 1 and 6.
  */
  private int randomNumber() {
    int randint = random.nextInt(5) + 1;
    return randint;
  }

  /**
   * In this function it will return won, lose or equal depending on who gets the 
   * highest number. You automaticly lose if you got over 10.
   */
  private String whoWillWin(int yourTot, int compTot) {
    String lose = "You lose!";
    String won = "You won!";

    if (yourTot >= 10) {
      return lose;
    }
    if (compTot >= 10) {
      return won;
    }
    if (yourTot > compTot) {
      return won;
    }
    if (compTot > yourTot) {
      return lose;
    }
    String equal = "It is equal!";
    return equal;
  }
}

