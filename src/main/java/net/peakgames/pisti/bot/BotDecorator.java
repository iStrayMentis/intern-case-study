package net.peakgames.pisti.bot;

import net.peakgames.pisti.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class BotDecorator implements Bot {

    private Bot bot;
    private int seat;
    private int score = 0;
    private List<Card> hand;

    public BotDecorator(int seat, Bot bot) {
        this.seat = seat;
        this.bot = bot;
    }

    @Override
    public void gameStarted(int seat, List<Card> discardPile) {
        bot.gameStarted(seat, new ArrayList<Card>(discardPile));
    }

    @Override
    public void dealt(List<Card> hand) {
        this.hand = hand;
        bot.dealt(new ArrayList<Card>(this.hand));
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
        hand.remove(card);
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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public String scoreWithBotName() {
        return this.bot.getClass().getName() + " (" + seat + ") : " + this.score;
    }

    public String getBotName() {
        return this.bot.getClass().getName();
    }
}
