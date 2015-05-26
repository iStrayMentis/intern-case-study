package net.peakgames.pisti.deck;

import net.peakgames.pisti.utility.ShuffleHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Deck items containing 52 Cards.
 * Deck can  be reused if shuffled in any given time.
 *
 * @see net.peakgames.pisti.deck.Card
 *
 * @author Peak Games
 */
public class Deck {

    public static final int NUM_CARDS = 52;
    private Card[] cards = new Card[52];
    private int index = 0;

    /**
     * Creates an ordered Card deck.
     */
    public Deck()
    {
        int counter = 0;
        for (Card.Type type : Card.Type.values()) {
            for (int i = 1; i < 14; i++) {
                cards[counter++] = Card.newCard(type, i);
            }
        }
    }

    /**
     * Shuffles an given array.
     */
    public void shuffle()
    {
        ShuffleHelper.shuffle(cards);
        index = 0;
    }

    /**
     * Return numOfCards cards from deck in a list.
     *
     * Throws RuntimeException if you try to draw cards more then left.
     *
     * @param numCards amount of cards that needs to be draw.
     * @return
     */
    public List<Card> drawCard(int numCards)
    {
        if (index + numCards > NUM_CARDS) {
            throw new RuntimeException("Not enough cards left in deck!");
        }

        ArrayList drawnCards = new ArrayList(numCards);
        for (int x = 0; x < numCards; x++) {
            drawnCards.add(cards[index++]);
        }

        return drawnCards;
    }

    public boolean isEmpty()
    {
        return index == NUM_CARDS;
    }

    public int numberOfCardsLeftInDeck()
    {
        return NUM_CARDS-index;
    }

}
