package model.rules;

import model.Player;

/**
 * How to decide the winning rules.
 *
 */
public interface WinRules {
  Player winner(Player dealer, Player player);

}
