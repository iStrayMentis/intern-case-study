package net.peakgames.pisti;

import java.util.Stack;

public class Game {
    private BotWrapper[] bots;

    public Game(Bot[] bots) {
        this.bots = new BotWrapper[4];
        shuffleBots(bots);
        for (int i = 0; i < 4; i++) {
            this.bots[i] = new BotWrapper(i, bots[i]);
        }
    }

    public GameResult executeGame() {
        Deck deck = createDeck();
        Stack<Card> discardPile = new Stack<Card>();

        //yere 4 kart ayirdik
        discardPile.addAll(deck.drawCard(4));

        for (BotWrapper bot : bots) {
            bot.gameStarted(discardPile);
        }

        int turnCount = 0;
        while (!deck.isEmpty()) {

            dealCardsIfNecessary(deck, turnCount);

            for (BotWrapper bot : this.bots) {
                Card card = bot.play();

                discardPile.push(card);
                broadcastCardPlay(bot.getSeat(), card);

                if (shouldCollect(discardPile)) {
                    broadcastCollectedEvent(bot.getSeat(), discardPile);

                    //TODO ilkin
                    int score = ScoreHelper.calculate(discardPile);
                    bot.addScore(score);

                    discardPile.clear();
                }

            }
            turnCount++;
        }
        // TODO Hakan
        return null;
    }

    private void dealCardsIfNecessary(Deck deck, int turnCount) {
        if ((turnCount % 4) == 0) {
            dealCards(deck);
        }
    }

    private void dealCards(Deck deck) {
        for (Bot bot : bots) {
            bot.dealed(deck.drawCard(4));
        }
    }

    private void broadcastCollectedEvent(int winnerIndex, Stack<Card> discardPile) {
        for (Bot bot : this.bots) {
            bot.collected(winnerIndex, discardPile);
        }
    }

    //TODO Hakan
    private boolean shouldCollect(Stack<Card> discardPile) {
        return false;
    }

    private void broadcastCardPlay(int seat, Card card) {
        for (Bot bot : bots) {
            bot.played(seat, card);
        }
    }

    //TODO Kaan
    private void shuffleBots(Bot[] bots) {
    }

    //TODO Kaan
    private Deck createDeck() {
        return null;
    }
}
