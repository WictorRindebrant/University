package controller;

import model.Game;
import view.View;

/**
 * Scenario controller for playing the game.
 */
public class Player implements model.Tracker {
  private Game model;
  private View view;

  /**
   * Runs the play use case.
   *
   * @param model The Model to use.
   * @param view  The view to use.
   */

  public Player(Game model, View view) {
    this.model = model;
    this.view = view;

    model.addSubscriber(this);
  }

  /**
   * Runs the game.
   *
   * @param game The game to use.
   * @param view The view to use.
   * @return Return input.
   */

  public boolean play(Game game, View view) {
    view.displayWelcomeMessage();

    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    View.GameOption input = view.getInput();

    if (input == View.GameOption.New_Game) {
      game.newGame();
    } else if (input == View.GameOption.Hit) {
      game.hit();
    } else if (input == View.GameOption.Stand) {
      game.stand();
    }

    return input != View.GameOption.Quit;
  }

  /**
   * Updates the hand.
   *
   * @param player Type of player in string format.
   * @param c      dealt card.
   */

  public void updatedHand(String player, model.Card c) {
    if (player.equals("Player")) {
      view.displayPlayerDrawn(c);
      view.pause();
    } else {
      view.displayDealerDrawn(c);
      view.pause();
    }
  }
}