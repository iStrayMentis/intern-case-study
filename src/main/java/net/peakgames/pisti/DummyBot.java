package net.peakgames.pisti;

import java.util.List;

public class DummyBot implements  Bot {

    private List<Card> hand;
    private Card discardCard;

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {

    }

    @Override
    public void dealed(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void played(int seat, Card card) {
        this.discardCard = card;
    }

    @Override
    public Card play() {

        if (hand == null || hand.isEmpty()) {
            throw new RuntimeException("No cards left in hand.");
        }

        Card playCard = hand.get(0);

        if(hand.contains(discardCard)) {
            playCard = discardCard;
        }

        else {

            for (Card card : hand) {
                if (card.getValue() < playCard.getValue()) {
                    playCard = card;
                }

                if (card.isJack()) {
                    playCard = card;
                    break;
                }
            }
        }

        hand.remove(playCard);
        return playCard;
    }

    @Override
    public void collected(int seat, List<Card> cards) {

    }
}
