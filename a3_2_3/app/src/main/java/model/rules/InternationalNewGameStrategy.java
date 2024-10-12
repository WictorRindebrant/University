package model.rules;


import model.Dealer;
import model.Deck;
import model.Player;


class InternationalNewGameStrategy implements NewGameStrategy {

  public boolean newGame(Deck deck, Dealer dealer, Player player) {
    
    dealer.dealCard(player);
    dealer.dealCard(dealer);
    dealer.dealCard(player);
    
    
    
    
    // Card.Mutable c;

    // c = deck.getCard();
    // c.show(true);
    // player.dealCard(c);

    // c = deck.getCard();
    // c.show(true);
    // dealer.dealCard(c);

    // c = deck.getCard();
    // c.show(true);
    // player.dealCard(c);

    return true;
  }
}