package model.rules;

import model.Player;

/**
 * Custom rules, player wins when tied.
 */

public class Custom implements WinRules {

  /**
   * Custom rules.
   *
   * @player The one who plays.
   * @dealer The one who deals.
   * @return dealer or player depending on who wins.
   */

  public Player winner(Player dealer, Player player) {
    if (dealer.calcScore() > 21) {
      return player;
    }
    if (player.calcScore() > 21) {
      return dealer;
    }
    if (dealer.calcScore() <= player.calcScore()) {
      return player;
    } else {
      return dealer;
    }
  }
}
