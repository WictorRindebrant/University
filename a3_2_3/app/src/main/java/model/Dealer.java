package model;

import java.util.ArrayList;
import java.util.Collection;
import model.Card.Mutable;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;

/**
 * Represents a dealer player that handles the deck of cards and runs the game
 * using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private ArrayList<Tracker> subscribers;

  /**
   * Initializing constructor.
   *
   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {

    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    subscribers = new ArrayList<Tracker>();
  }

  public void addSubscriber(Tracker newSubscriber) {
    subscribers.add(newSubscriber);
  }

  /**
   * Updates all subscribers.
   *
   * @param playerType Type of player.
   * @param c          card.
   */

  public void alertSub(String playerType, model.Card c) {
    for (Tracker subscriber : subscribers) {
      subscriber.updatedHand(playerType, c);
    }
  }

  /**
   * Starts a new game if the game is not currently under way.
   *
   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(deck, this, player);
    }
    return false;
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.
   *
   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      Card.Mutable c;
      c = deck.getCard();
      c.show(true);
      player.dealCard(c);

      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.
   *
   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    if (player.calcScore() > maxScore) {
      return true;
    } else if (calcScore() > maxScore) {
      return false;
    }
    return calcScore() >= player.calcScore();
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.
   *
   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has choosen to take no more cards, it is the dealers turn.
   *
   * @return True or False depending on if the player stands.
   */

  public boolean stand() {
    if (this.deck != null) {
      showHand();
      while (hitRule.doHit(this)) {
        hitRule.doHit(this);
        Mutable c = deck.getCard();
        c.show(true);
        dealCard(c);
      }
      return true;
    }
    return false;
  }

  /**
   * Gives player a card.
   *
   * @param player Shows player that recieves card.
   */

  public void dealCard(Player player) {
    if (player instanceof Dealer) {
      if (((Collection<?>) player.getHand()).size() == 1) {
        Card.Mutable c = deck.getCard();
        c.show(false);
        player.dealCard(c);
        alertSub("Dealer ", c);
      } else {
        Card.Mutable c = deck.getCard();
        c.show(true);
        player.dealCard(c);
        alertSub("Dealer ", c);
      }
    } else {
      Card.Mutable c = deck.getCard();
      c.show(true);
      player.dealCard(c);
      alertSub("player ", c);
    }
  }

}