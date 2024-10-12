package assignment1;

import java.util.Random;
import java.util.Scanner;

/**
 * The class for the Ant.
 */
public class Ants {
  Random random = new Random();

  /**
   * The main program for the Ant.
   */
  public void theAnt(Scanner scan) {
    System.out.println("Ants");
    System.out.println("=====");
    System.out.println();

    double totSteps = 0;

    for (int i = 1; i <= 10; i++) {
      int[][] chessBoard = createBoard();
      boolean[] checkChessboard = new boolean[64];

      int steps = 0;
      int x = random.nextInt(7);
      int y = random.nextInt(7);
      
      int removeNumber = (chessBoard[x][y]);
      checkChessboard[removeNumber] = true;

      boolean allTrue = true;
      while (allTrue == true) {
        int positionToMove = random.nextInt(4) + 1;
        if (positionToMove == 1) {
          if (y < 7) {
            y += 1;
            steps++;
          }
        } else if (positionToMove == 2) {
          if (y > 0) {
            y -= 1;
            steps++;
          }
        } else if (positionToMove == 3) {
          if (x < 7) {
            x += 1;
            steps++;
          }
        } else if (positionToMove == 4) {
          if (x > 0) {
            x -= 1;
            steps++;
          }
        }
        removeNumber = (chessBoard[x][y]);
        checkChessboard[removeNumber - 1] = true;

        for (int j = 0; j  < 64; j++) {
          if (checkChessboard[j] == false) {
            allTrue = true;
            break;
          }
          allTrue = false;
        }
      }
      System.out.println("Number of steps in simulation " + i + ": " + steps);
      totSteps += steps;
    }
    double aveSteps = totSteps / 10;
    System.out.println("Average amount of steps: " + aveSteps);
  }

  /**
   * The function that creates the chessboard.
   */
  private int[][] createBoard() {
    int[][]chessboard = new int[8][8];
    
    int fir = 0;
    int sec = 0;
    for (int i = 1; i <= 64; i++) {
      chessboard[fir][sec] = i;
      if (i % 8 == 0) {
        sec++;
        fir = -1;
      }
      fir++;
    }
    return chessboard;
  }
}
