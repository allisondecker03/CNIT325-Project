package Game;

import java.util.ArrayList;
import java.util.List;

public class WarPlayer {
    private final String name; //name of player
    private final List<Card> hand; //cards in player's hand

    //Constructor that creates new player
    public WarPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    //add a card to player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    //get the cards in player's hand
    public List<Card> getHand() {
        return hand;
    }

    //get the name of player
    public String getName() {
        return name;
    }

    //calculate value of the cards in hand
    public int getHandValue() {
        int totalValue = 0;
        for (Card card : hand) {
            totalValue += card.getValue();
        }
        return totalValue;
    }

    //clear the hands
    public void clearHand() {
        hand.clear();
    }
}

