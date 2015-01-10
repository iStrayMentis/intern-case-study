package net.peakgames.pisti;


import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ScoreHelperTest {

    @Test
    public void scoreShouldBeZeroIfDiscardPileIsEmptyOrNull() {
        assertEquals(0, ScoreHelper.calculate(null));
        assertEquals(0, ScoreHelper.calculate(new Stack<Card>()));
    }

    @Test
    public void scoreOf_each_jack_should_be_one() {
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.CLUBS, Card.JACK)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.SPADES, Card.JACK)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.HEARTS, Card.JACK)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.DIAMONDS, Card.JACK)));
    }

    @Test
    public void scoreOf_each_ace_should_be_one() {
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.CLUBS, 1)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.SPADES, 1)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.HEARTS, 1)));
        assertEquals(1, ScoreHelper.scoreOf(Card.newCard(Card.Type.DIAMONDS, 1)));
    }

    @Test
    public void scoreOf_clubs_two_should_be_two() {
        assertEquals(2, ScoreHelper.scoreOf(Card.newCard(Card.Type.CLUBS, 2)));
    }

    @Test
    public void scoreOf_diamonds_ten_should_be_three() {
        assertEquals(3, ScoreHelper.scoreOf(Card.newCard(Card.Type.DIAMONDS, 10)));
    }

    @Test
    public void score_should_be_sum_of_scores_of_all_cards() {
        Stack<Card> cards = new Stack<Card>();
        cards.push(Card.newCard(Card.Type.DIAMONDS, 3));
        cards.push(Card.newCard(Card.Type.DIAMONDS, 10));
        cards.push(Card.newCard(Card.Type.HEARTS, Card.JACK));
        cards.push(Card.newCard(Card.Type.HEARTS, 7));
        cards.push(Card.newCard(Card.Type.CLUBS, Card.ACE));
        cards.push(Card.newCard(Card.Type.CLUBS, Card.QUEEN));
        cards.push(Card.newCard(Card.Type.SPADES, 9));
        cards.push(Card.newCard(Card.Type.SPADES, Card.KING));

        assertEquals(5, ScoreHelper.calculate(cards));
    }

    @Test
    public void score_of_each_pisti_should_be_ten() {
        Stack<Card> cards = new Stack<Card>();
        cards.push(Card.newCard(Card.Type.DIAMONDS, 3));
        cards.push(Card.newCard(Card.Type.CLUBS, 3));
        assertEquals(10, ScoreHelper.calculate(cards));

        cards = new Stack<Card>();
        cards.push(Card.newCard(Card.Type.DIAMONDS, Card.JACK));
        cards.push(Card.newCard(Card.Type.CLUBS, Card.JACK));
        assertEquals(10, ScoreHelper.calculate(cards));
    }

}
