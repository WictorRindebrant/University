package cookbook;

import java.sql.SQLException;

public class Test {
  /**
   * Main for test and test if this works.
   *
   */
  public static void main(String[] args) throws SQLException{
    //Connect and create.
    //Query q = new Query();
    
    System.out.println("Start Loading");

    int[] in = new int[9];
    in[1] = 1;

    if(in != null && in[1] > 0) {
      System.out.println("ey");
    }

    System.out.println("\nEND");





  }
}
