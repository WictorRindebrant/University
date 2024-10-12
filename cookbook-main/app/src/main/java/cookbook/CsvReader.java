package cookbook;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Get data from csv files.
 */
public class CsvReader {

  /**
   * Return the data that is contained in the csv file.
   *
   * @param path path to the csv file.
   * @return ArrayList containing lists of strings.
   */
  public ArrayList<String[]> getData(String path) {

    ArrayList<String[]> data = new ArrayList<>();
    try {
      FileInputStream stream = new FileInputStream(path);
      Scanner input = new Scanner(stream, "utf-8");
      input.nextLine();
      while (input.hasNext()) {
        data.add(input.nextLine().split(","));
      }
      input.close();
      return data;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}