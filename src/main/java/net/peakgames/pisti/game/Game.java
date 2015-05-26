package net.peakgames.pisti.game;

import java.util.Stack;

import net.peakgames.pisti.deck.Card;
import net.peakgames.pisti.deck.Deck;
import net.peakgames.pisti.bot.Bot;
import net.peakgames.pisti.bot.BotDecorator;
import net.peakgames.pisti.utility.ScoreHelper;
import net.peakgames.pisti.utility.ShuffleHelper;

/**
 * Plays the game, deals cards, calls bots to play a card, calculates who collects and scores.
 *
 * @see net.peakgames.pisti.deck.Deck
 * @see net.peakgames.pisti.bot.BotDecorator
 * @see net.peakgames.pisti.utility.ScoreHelper;
 *
 * @author Peak Games
 */
public class Game {

    public static final int NUM_SEATS = 4;
    public static final int NUM_DRAW_CARDS = 4;

    private BotDecorator[] bots;

    Game() {

    }

    /**
     * Construct a game with given bots.
     *
     * @param bots to play the game.
     */
    public Game(Bot[] bots)
    {
        this.bots = new BotDecorator[NUM_SEATS];
        shuffleBots(bots);
        for (int i = 0; i < NUM_SEATS; i++) {
            this.bots[i] = new BotDecorator(i, bots[i]);
        }
    }

    /**
     * Executes the game till to end of all cards in a deck.
     *
     * @return result of the game.
     */
    public GameResult executeGame()
    {
        Deck deck = createDeck();
        Stack<Card> discardPile = new Stack<Card>();

        discardPile.addAll(deck.drawCard(NUM_DRAW_CARDS));

        for (BotDecorator bot : bots) {
            bot.gameStarted(discardPile);
        }

        int turnCount = 0;
        while (!deck.isEmpty())
        {
            dealCardsIfNecessary(deck, turnCount);

            for (BotDecorator bot : this.bots) {
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

    boolean shouldCollect(Stack<Card> discardPile, Deck deck, int seat)
    {
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
        if(lastDiscardCard.getValue() == beforeDiscardCard.getValue()) {
            return true;
        }

        return false;
    }

    private void dealCardsIfNecessary(Deck deck, int turnCount)
    {
        if ((turnCount % NUM_SEATS) == 0) {
            dealCards(deck);
        }
    }

    private void dealCards(Deck deck)
    {
        for (Bot bot : bots) {
            bot.dealt(deck.drawCard(NUM_DRAW_CARDS));
        }
    }

    private void broadcastCollectedEvent(int winnerIndex, Stack<Card> discardPile)
    {
        for (Bot bot : this.bots) {
            bot.collected(winnerIndex, discardPile);
        }
    }

    private void broadcastCardPlay(int seat, Card card)
    {
        for (Bot bot : bots) {
            bot.played(seat, card);
        }
    }


    private void shuffleBots(Bot[] bots)
    {
        ShuffleHelper.shuffle(bots);
    }


    private Deck createDeck()
    {
        Deck d = new Deck();
        d.shuffle();
        return d;

    }

}
