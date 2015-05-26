package net.peakgames.pisti.bot;

import net.peakgames.pisti.deck.Card;

import java.util.List;

/**
 * Bot interface defines the protocol of a concrete Bot that will be used by Game.
 *
 * @see net.peakgames.pisti.deck.Card
 * @see net.peakgames.pisti.bot.SUperDummyBot
 *
 * @author Peak Games
 */
public interface Bot {

    /**
     * Bot gets information about the game is started.
     *
     * @param seat position of the bot.
     * @param discardPile initial list of discarded cards.
     */
    public void gameStarted(int seat, List<Card> discardPile);

    /**
     * Bot gets information about his own cards that has been dealt by the game.
     *
     * @param hand the cards that the bot have.
     */
    public void dealt(List<Card> hand);

    /**
     * Bot gets information about who played which card.
     *
     * @param seat position of the player bot.
     * @param card the played card.
     */
    public void played(int seat, Card card);

    /**
     * Bot gets information about who collected the cards and which cards.
     *
     * @param seat position of the bot who collected cards.
     * @param cards the collected cards.
     */
    public void collected(int seat, List<Card> cards);

    /**
     * Bots discards this card, when game ask for it.
     *
     * @return the card that bot discards.
     */
    public Card play();

}
