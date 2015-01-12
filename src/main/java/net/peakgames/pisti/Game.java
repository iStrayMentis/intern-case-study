package net.peakgames.pisti;

import net.peakgames.pisti.utilities.ShuffleHelper;

import java.util.Stack;

public class Game {

    public static final int NUM_SEATS = 4;
    public static final int NUM_DRAW_CARDS = 4;


    private BotWrapper[] bots;

    Game() {

    }

    public Game(Bot[] bots) {
        this.bots = new BotWrapper[NUM_SEATS];
        shuffleBots(bots);
        for (int i = 0; i < NUM_SEATS; i++) {
            this.bots[i] = new BotWrapper(i, bots[i]);
        }
    }

    public GameResult executeGame() {
        Deck deck = createDeck();
        Stack<Card> discardPile = new Stack<Card>();

        //yere 4 kart ayirdik
        discardPile.addAll(deck.drawCard(NUM_DRAW_CARDS));

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

                if (shouldCollect(discardPile, deck, bot.getSeat())) {
                    broadcastCollectedEvent(bot.getSeat(), discardPile);

                    int score = ScoreHelper.calculate(discardPile);
                    bot.addScore(score);
                    //TODO majority of cards 3 points.
                    discardPile.clear();
                }

            }
            turnCount++;
        }

        return new GameResult(bots);
    }

    private void dealCardsIfNecessary(Deck deck, int turnCount) {
        if ((turnCount % NUM_SEATS) == 0) {
            dealCards(deck);
        }
    }

    private void dealCards(Deck deck) {
        for (Bot bot : bots) {
            bot.dealed(deck.drawCard(NUM_DRAW_CARDS));
        }
    }

    private void broadcastCollectedEvent(int winnerIndex, Stack<Card> discardPile) {
        for (Bot bot : this.bots) {
            bot.collected(winnerIndex, discardPile);
        }
    }

    boolean shouldCollect(Stack<Card> discardPile, Deck deck, int seat) {

        if(discardPile == null || deck == null || seat >= NUM_SEATS) {
            return false;
        }

        if(discardPile.size() <= 1) {
            return false;
        }

        if(deck.isEmpty() && seat == NUM_SEATS-1) {
            return true;
        }

        Card lastDiscardCard = discardPile.peek();
        if(lastDiscardCard.isJack()) {
            return true;
        }

        Card beforeDiscardCard = discardPile.get(discardPile.size()-2);
        if(lastDiscardCard.equals(beforeDiscardCard)) {
            return true;
        }

        return false;
    }

    private void broadcastCardPlay(int seat, Card card) {
        for (Bot bot : bots) {
            bot.played(seat, card);
        }
    }


    private void shuffleBots(Bot[] bots) {
        ShuffleHelper.shuffle(bots);
    }


    private Deck createDeck() {
        Deck d = new Deck();
        d.shuffle();
        return d;

    }
}
