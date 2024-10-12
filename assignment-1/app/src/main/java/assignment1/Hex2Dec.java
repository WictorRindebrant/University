package assignment1;

import java.util.Scanner;

/**
* This is the class for Task8.
*/
public class Hex2Dec {
  /**
   * This function lets the user enter a hexdecimal as a string, then the string is
   * converted to a number of int in the function below, then it is printed as both hexdecimal
   * and as number of ints in this function.
   */
  public void hex2Dec(Scanner input) {
    System.out.print("Enter a hex number: ");
    String hex = input.next();

    int number = hexConverter(hex);
    System.out.println("The decimall value for " + hex + " is " + number + ".");
  }

  /**
   * This function will convert a hexed string to an number of integers.
   */
  private int hexConverter(String hex) {
    double total = 0;
    int raised = hex.length() - 1;
    for (int i = 0; i < hex.length(); i++) {
      char charHex = hex.charAt(i);
      String stringHex = Character.toString(charHex);
      int number = Integer.parseInt(stringHex, 16);
      total = total + (number * Math.pow(16, raised));
      raised--;
    }
    int totals = (int) total;
    return totals;
  }
}
