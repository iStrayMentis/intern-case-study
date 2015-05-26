package net.peakgames.pisti.utility;

import net.peakgames.pisti.deck.Card;
import java.util.Stack;

/**
 * Calculates the score of a given card stack.
 *
 * @see net.peakgames.pisti.deck.Card
 *
 * @author Peak Games
 */
public class ScoreHelper {

    public static int calculate(Stack<Card> cards)
    {
        if (cards == null || cards.isEmpty()) {
            return 0;
        }

        if (cards.size() == 2)
        {
            Card card1 = cards.pop();
            Card card2 = cards.pop();
            if (card1.getValue() == card2.getValue()) {
                if(card1.isJack()) {
                    return 20;
                }
                else {
                    return 10;
                }
            }
        }

        int score = 0;
        for (Card card : cards) {
            score += scoreOf(card);
        }
        return score;
    }

    /**
     * Score of a single card.
     *
     * @param card
     * @return score of the card.
     */
    public static int scoreOf(Card card)
    {
        if (card.isAce()) {
            return 1;
        }

        if (card.isJack()) {
            return 1;
        }

        if (card.getValue() == 2 && card.getType() == Card.Type.CLUBS) {
            return 2;
        }

        if (card.getValue() == 10 && card.getType() == Card.Type.DIAMONDS) {
            return 3;
        }

        return 0;
    }
}
