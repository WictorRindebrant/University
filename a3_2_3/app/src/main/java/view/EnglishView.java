package view;

/**
 * Implements an english console view.
 */
public class EnglishView implements View {

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }
    System.out.println("Hello Black Jack World");
    System.out.println("Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n");
  }

  /**
   * Returns pressed characters from the keyboard.
   *
   * @return the pressed character.
   */
  public GameOption getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return inputToEnum(c);
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return inputToEnum(0);
    }
  }

  private GameOption inputToEnum(int input) {
    if (input == 'p') {
      return GameOption.New_Game;

    } else if (input == 'h') {
      return GameOption.Hit;

    } else if (input == 's') {
      return GameOption.Stand;

    } else {
      return GameOption.Quit;
    }
  }

  public void displayCard(model.Card card) {
    System.out.println("" + card.getValue() + " of " + card.getColor());
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Player", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Dealer", hand, score);
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Has: ");
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Score: " + score);
    System.out.println("");
  }

  public void displayDealerDrawn(model.Card c) {
    System.out.println("Dealer gets new card (" + c.getValue() + " of " + c.getColor() + ")");

  }

  public void displayPlayerDrawn(model.Card c) {
    System.out.println("Player gets new card (" + c.getValue() + " of " + c.getColor() + ")");
  }

  /**
   * Displays the winner of the game.
   *
   * @param dealerIsWinner True if the dealer is the winner.
   */

  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("GameOver: ");
    if (dealerIsWinner) {
      System.out.println("Dealer Won!");
    } else {
      System.out.println("You Won!");
    }
  }

  /**
   * Pauses the printing.
   */

  public void pause() {
    try {
      Thread.sleep(750);
      System.out.println("*pause*");
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
