package net.peakgames.pisti;

public class Card {
    public static enum Type {
        SPADES, HEARTS, DIAMONDS, CLUBS
    }

    private Type type;
    private int value;

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
}
