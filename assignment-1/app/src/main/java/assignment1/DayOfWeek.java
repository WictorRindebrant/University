package assignment1;

import java.util.Scanner;

/**
* This is the class for Task6.
*/
public class DayOfWeek {
  /**
  * This function is used for letting the user enter a year, month and day of the moth
  * (as an integer). Then depending on what value the function below return, it will print
  * the day it is for that date.
  */
  public void dayOfWeek(Scanner input) {
    System.out.print("Enter year: ");
    int year = input.nextInt();

    System.out.print("Enter month (1-12): ");
    int month = input.nextInt();

    System.out.print("Enter day of the month (1-31): ");
    int daymonth = input.nextInt();

    int day = dayOfWeekFormula(year, month, daymonth);

    if (day == 0) {
      System.out.println("Day of the week is Saturday");
    } else if (day == 1) {
      System.out.println("Day of the week is Sunday");
    } else if (day == 2) {
      System.out.println("Day of the week is Monday");
    } else if (day == 3) {
      System.out.println("Day of the week is Thuesday");
    } else if (day == 4) {
      System.out.println("Day of the week is Wedenesday");
    } else if (day == 5) {
      System.out.println("Day of the week is Thursday");
    } else if (day == 6) {
      System.out.println("Day of the week is Friday");
    }
  }

  /**
  * This function is used for calculating the day of the week by sending in the year, month
  * and day of the month ( in number ). It uses special cases for January and February (making
  * the month value to 13 and 14 if it's 1 and 2 and changing year to (the year - 1)).
  */
  private int dayOfWeekFormula(int year, int month, int daymonth) {
    int q = daymonth;
    int m = month;
    int y = year;
    if (m == 1) {
      m = 13;
      y--;
    }
    if (m == 2) {
      m = 14;
      y--;
    }
    int k = y % 100;
    int j = y / 100;
    int h = ((q + ((26 * (m + 1)) / 10) + k + (k / 4) + (j / 4) + (5 * j)) % 7);
    return h;
  }
}
