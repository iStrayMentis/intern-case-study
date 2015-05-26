package net.peakgames.pisti.bot;

import net.peakgames.pisti.deck.Card;

import java.util.List;

public class SuperDummyBot implements Bot  {
    private List<Card> hand;

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {

    }

    @Override
    public void dealt(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void played(int seat, Card card) {

    }

    @Override
    public Card play() {
        return hand.remove(0);
    }

    @Override
    public void collected(int seat, List<Card> cards) {

    }
}
