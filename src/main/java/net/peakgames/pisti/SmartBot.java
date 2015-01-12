package net.peakgames.pisti;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SmartBot implements Bot {
    private List<Card> hand;
    private Stack<Card> discardedCards = new Stack<Card>();
    private Set<Card> groundCards = new HashSet<Card>();
    private int [] values = new int[14];

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {
        discardedCards.addAll(discardPile);
        groundCards.addAll(discardPile);
        for(Card card : discardPile) {
            values[card.getValue()] = values[card.getValue()] + 1;
        }
    }

    @Override
    public void dealed(List<Card> hand) {
        this.hand = hand;
    }

    @Override
    public void played(int seat, Card card) {
        discardedCards.add(card);
        groundCards.add(card);
        values[card.getValue()] = values[card.getValue()] + 1;
    }

    @Override
    public Card play() {

        if (hand == null || hand.isEmpty()) {
            throw new RuntimeException("No cards left in hand.");
        }

        Card playCard = findBestCard();

        hand.remove(playCard);
        return playCard;
    }

    private Card findBestCard() {
        for (Card card : hand) {
            if (card.getValue() == discardedCards.peek().getValue()) {
                return card;
            }
        }

        for (Card card : hand) {

            if (!groundCards.isEmpty() && card.isJack()) {
                return card;
            }
        }

        Card playCard = hand.get(0);

        int minFreq = Integer.MAX_VALUE;
        for (Card card : hand) {
            int val = values[card.getValue()];
            if (val<minFreq) {
                minFreq = val;
                playCard = card;
            }
        }
        return playCard;
    }

    @Override
    public void collected(int seat, List<Card> cards) {
        groundCards.clear();
    }
}
