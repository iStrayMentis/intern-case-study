package net.peakgames.pisti;

import java.util.Stack;

public class ScoreHelper {

    public static int calculate(Stack<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            return 0;
        }

        if (cards.size() == 2) {
            Card card1 = cards.pop();
            Card card2 = cards.pop();
            if (card1.getValue() == card2.getValue()) {
                return 10;
            }
        }

        int score = 0;
        for (Card card : cards) {
            score += scoreOf(card);
        }
        return score;
    }

    public static int scoreOf(Card card) {
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
