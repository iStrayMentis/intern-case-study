package net.peakgames.pisti;

public class Card {
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static enum Type {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    private final Type type;
    private final int value;

    private Card(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    public static Card newCard(Type type, int value) {
        return new Card(type, value);
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean isJack() {
        return value == JACK;
    }

    public boolean isAce() {
        return value == ACE;
    }
}
