package Game;

public class Card {
    //Enum for suits of cards
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    //Enum for thirteen types of cards
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private final Suit suit; //suit of the card
    private final Rank rank; //rank of the card

    //Constructor to create new card with specified suit and value
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    //get value of card
    public int getValue() {
        return rank.ordinal() + 1;
    }

    //return string representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
