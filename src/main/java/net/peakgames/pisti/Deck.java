package net.peakgames.pisti;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    //TODO Kaan
    public static final int NUM_CARDS = 52;
    private List<Card> cards = new ArrayList<Card>(NUM_CARDS);
    private int index = 0;

    public void shuffle() {
    }

    public List<Card> drawCard(int numCards) {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }
}
