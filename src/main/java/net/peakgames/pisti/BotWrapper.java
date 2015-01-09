package net.peakgames.pisti;


import java.util.List;

public class BotWrapper implements Bot {
    private Bot bot;
    private int seat;
    private List<Card> hand;

    public BotWrapper(int seat, Bot bot) {
        this.seat = seat;
        this.bot = bot;
    }

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {
        bot.gameStarted(seat, discardPile);
    }

    @Override
    public void dealed(List<Card> hand) {
        this.hand = hand;
        bot.dealed(hand);
    }

    @Override
    public void played(int seat, Card card) {
        bot.played(seat, card);
    }

    @Override
    public Card play() {
        Card card = bot.play();
        if ( ! hand.contains(card)) {
            throw new RuntimeException("Bir akilli sen misin? Cakaaal.");
        }
        return card;
    }

    @Override
    public void collected(int seat, List<Card> cards) {
        bot.collected(seat, cards);
    }


    public void gameStarted(List<Card> discardPile) {
        bot.gameStarted(this.seat, discardPile);
    }

    public int getSeat() {
        return seat;
    }

    public void addScore(int score) {

    }
}
