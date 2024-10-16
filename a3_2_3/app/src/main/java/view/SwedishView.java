package view;

/**
 * Implements a Swedish console view.
 */
public class SwedishView implements View {

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }

    System.out.println("Hej Black Jack Världen");
    System.out.println("----------------------");
    System.out.println("Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n");
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
    System.out.println(input);
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

  /**
   * Displays a card.
   *
   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
          "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
   * Displays the winner of the game.
   *
   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupiern Vann!");
    } else {
      System.out.println("Du vann!");
    }
  }

  public void displayDealerDrawn(model.Card c) {
    System.out.println("Dealer drar ett nytt kort (" + c.getValue() + " av " + c.getColor() + ")");
  }

  public void displayPlayerDrawn(model.Card c) {
    System.out.println("Spelare drar ett nytt kort (" + c.getValue() + " av " + c.getColor() + ")");
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Har: " + score);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Poäng: " + score);
    System.out.println("");
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
