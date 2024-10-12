package model.rules;

import model.Player;

/**
 * Standard rules.
 */

public class Standard implements WinRules {

  /**
   * If someone not soft then we calculate who wins.
   *
   * @dealer the one who you will be playing against.
   * @player the perosn you will play as.
   */

  public Player winner(Player dealer, Player player) {
    if (dealer.calcScore() > 21) {
      return player;
    }
    if (player.calcScore() > 21) {
      return dealer;
    }
    if (player.calcScore() <= dealer.calcScore()) {
      return dealer;
    } else {
      return player;
    }
  }
}
