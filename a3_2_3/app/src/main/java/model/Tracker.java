package model;

/**
 * Observer interface for tracking the actions of the game.
 */

public interface Tracker {
  /**
   * Observer to track subscribers.
   *
   * @param player Tracking the player.
   * @param c      Card.
   */
  void updatedHand(String player, model.Card c);
}
