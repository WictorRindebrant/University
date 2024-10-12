package assignment1;

import java.util.Scanner;

/**
* This is the class for Task1.
*/
public class Time {
  /**
   * In this function it will convert secunds in to hours, minutes and secunds.
   */
  public void timeConverter(Scanner input) {

    int time = enterTime(input);
    int hour = time / 3600;
    int min = (time / 60) - (hour * 60);
    int sec = time - (min * 60) - (hour * 3600);
    System.out.print("This corresponds to: ");
    System.out.println(hour + " hours, " + min + " minutes and " + sec + " seconds.");
  }

  /**
   * In this function it will ask the user to enter an int input in secunds.
   */
  private int enterTime(Scanner inputScanner) {
    System.out.print("Give a number of secunds: ");
    int intInput = inputScanner.nextInt();
    return intInput;
  }
}
