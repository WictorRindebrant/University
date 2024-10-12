package model.rules;

import model.Card;
import model.Player;

/**
 * Soft 17 hit strategy class.
 */
public class SoftHitStrategy implements HitStrategy {
  private static final int hitLimit = 17;

  /**
   * Soft hit rules.
   *
   * @param dealer dealer object.
   * @return true of false dependig on if the dealer should take another card.
   */
  public boolean doHit(Player dealer) {
    int score = dealer.calcScore();
    for (Card c : dealer.getHand()) {
      if (c.getValue() == Card.Value.Ace && score == hitLimit) {
        return softHit(dealer);
      }
    }
    return score < 17;
  }

  /**
   * Inplemantation of soft hit rule.
   *
   * @param dealer dealer object.
   * @return true of false dependig on if the dealer should take another card.
   */
  private boolean softHit(Player dealer) {
    int score = dealer.calcScore();
    int aceValue = 0;
    for (Card c : dealer.getHand()) {
      if (c.getValue() == Card.Value.Ace) {
        aceValue += 10;
      }
    }

    return score - aceValue < 17;
  }
}