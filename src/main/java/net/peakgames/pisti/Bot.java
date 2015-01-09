package net.peakgames.pisti;

import java.util.List;

public interface Bot {
    public void gameStarted(int seat, List<Card> discardPile);
    public void dealed(List<Card> hand);
    public void played(int seat, Card card);
    public Card play();
    public void collected(int seat, List<Card> cards);
}
