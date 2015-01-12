package net.peakgames.pisti;

import java.util.List;

public class SuperDummyBot implements Bot  {
    private List<Card> hand;

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {

    }

    @Override
    public void dealed(List<Card> hand) {

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
