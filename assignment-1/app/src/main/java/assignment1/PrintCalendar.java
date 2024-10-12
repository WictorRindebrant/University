package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

/**
* This is the class for Task9.
*/
public class PrintCalendar {
  /**
   * This function is used for calling all the other functions and printing out the
   * calender depending on all the values it has recived from the other functions and
   * the inputs from the user.
   */
  public void printCalender(Scanner input) {
    System.out.print("Enter a year after 1800: ");
    int year = input.nextInt();

    System.out.print("Enter a month (1-12): ");
    int intMonth = input.nextInt();

    String strMonth = monthConverter(intMonth);
    System.out.println(strMonth + " " + year);
    frame();

    int day = dayOfWeekFormula(year, intMonth);
    day = fixedDay(day);

    ArrayList<String> calenderDays = calenderList(day);

    int numberOfMonth = numberMonth(intMonth, year);
    int calenderCounter = 0;
    for (int i = 0; i <= numberOfMonth + day - 1; i++) {
      if (calenderCounter < 6) {
        System.out.print("  " + calenderDays.get(i));
        calenderCounter++;
      } else {
        System.out.println("  " + calenderDays.get(i));
        calenderCounter = 0;
      }
    }
  }

  /**
  * This function is creating a ArrayList with 31 integers in it from 1 to 31.
  * And filling the first spots in the ArrayList with two spaces times how far that
  * months day 1 is from monday, that is calculated in the function dayOfWeekFormula.
  */
  private ArrayList<String> calenderList(int day) {
    ArrayList<String> calenderDays = new ArrayList<String>();
    if (day != 0) {
      for (int i = 0; i < day; i++) {
        calenderDays.add("  ");
      }
    }
    for (int i = 0; i < 31; i++) {
      if (i < 9) {
        calenderDays.add(" " + Integer.toString(i + 1));
      } else {
        calenderDays.add(Integer.toString(i + 1));
      }
    }
    return calenderDays;
  }

  /**
  * Since the function dayOfWeekFormula starting count on saturday as 0. This function changes so
  * that it does start count 0 from monday and not saturday.
  */
  private int fixedDay(int day) {
    if (day == 0) {
      day = 5;
    } else if (day == 1) {
      day = 6;
    } else if (day == 2) {
      day = 0;
    } else if (day == 3) {
      day = 1;
    } else if (day == 4) {
      day = 2;
    } else if (day == 5) {
      day = 3;
    } else if (day == 6) {
      day = 4;
    }
    return day;
  }

  /**
  * This function is used for calculating the first day of the month by sending in the year and
  * month ( in number ). It uses special cases for January and February (making
  * the month value to 13 and 14 if it's 1 and 2 and changing year to (the year - 1)).
  */
  private int dayOfWeekFormula(int year, int month) {
    int q = 1;
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

  /**
   * This function will just print out the top frame of the assignment.
   */
  private void frame() {
    System.out.println("-----------------------------");
    System.out.println(" Mon Tue Wed Thu Fri Sat Sun ");
  }

  /**
   * This function takes one argument (month) and will return how manny days
   * their is in that month.
   */
  private int numberMonth(int month, int year) {
    int daysInMonth = 0;
    if (month == 1) {
      daysInMonth = 31;
    } else if (month == 2) {
      if (year % 4 == 0) {
        if (year % 100 == 0 && year % 400 != 0) {
          daysInMonth = 28;
        } else {
          daysInMonth = 29;
        }
      } else {
        daysInMonth = 28;
      }
    } else if (month == 3) {
      daysInMonth = 31;
    } else if (month == 4) {
      daysInMonth = 30;
    } else if (month == 5) {
      daysInMonth = 31;
    } else if (month == 6) {
      daysInMonth = 30;
    } else if (month == 7) {
      daysInMonth = 31;
    } else if (month == 8) {
      daysInMonth = 31;
    } else if (month == 9) {
      daysInMonth = 30;
    } else if (month == 10) {
      daysInMonth = 31;
    } else if (month == 11) {
      daysInMonth = 30;
    } else if (month == 12) {
      daysInMonth = 31;
    }
    return daysInMonth;
  }

  /**
   * This function converts a number to that number as month.
   */
  private String monthConverter(int number) {
    String numberString = "";
    if (number == 1) {
      numberString = "January";
    } else if (number == 2) {
      numberString = "February";
    } else if (number == 3) {
      numberString = "March";
    } else if (number == 4) {
      numberString = "April";
    } else if (number == 5) {
      numberString = "May";
    } else if (number == 6) {
      numberString = "June";
    } else if (number == 7) {
      numberString = "July";
    } else if (number == 8) {
      numberString = "August";
    } else if (number == 9) {
      numberString = "September";
    } else if (number == 10) {
      numberString = "October";
    } else if (number == 11) {
      numberString = "November";
    } else if (number == 12) {
      numberString = "December";
    }
    return numberString;
  }
}
