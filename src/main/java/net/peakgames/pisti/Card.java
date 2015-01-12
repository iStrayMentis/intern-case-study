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
        if (type == null) throw new IllegalArgumentException("A card must have an type!");
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
    
    @Override
    public String toString() {
        return "Card{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (value != card.value) return false;
        if (type != card.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value;
        return result;
    }
}
