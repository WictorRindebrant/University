package assignment1;

import java.util.Scanner;

/**
* This is the class for Task3.
*/
public class DangerousWork {
  /**
   * In this function it will double the amount of kronor depending on the amount of
   * days. It will calculate the amount of days by adding one on days everytime it doubles
   * the kronor until the kronor is the same or just under the amount of kronor the user enters.
   */
  public void dangerousWork(Scanner input) {
    double kronor = 0.01;
    int days = 1;
    
    System.out.print("How much would you like to earn? ");
    int intInput = input.nextInt();

    while (kronor < intInput) {
      kronor = kronor * 2;
      days = days + 1;
    }
    if (kronor > intInput) {
      days = days - 1;
    }
    System.out.println("You will have your money in " + days + " days.");
  }
}
