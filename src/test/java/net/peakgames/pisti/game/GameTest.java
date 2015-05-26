package net.peakgames.pisti.game;

import net.peakgames.pisti.bot.Bot;
import net.peakgames.pisti.bot.SuperDummyBot;
import net.peakgames.pisti.deck.Card;
import net.peakgames.pisti.deck.Deck;
import net.peakgames.pisti.game.Game;
import net.peakgames.pisti.game.GameResult;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

public class GameTest {

    @Test
    public void shouldCollectForNullDiscardPile()
    {
        Game game = new Game();

        assertFalse(game.shouldCollect(null, new Deck(), 0));
    }

    @Test
    public void shouldCollectForNoCardsInPile()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        assertFalse(game.shouldCollect(discardPile, new Deck(), 0));
    }

    @Test
    public void shouldCollectForOneCardInPile()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        assertFalse(game.shouldCollect(discardPile, new Deck(), 0));


        discardPile.pop();
        discardPile.push(Card.newCard(Card.Type.CLUBS, Card.JACK));
        assertFalse(game.shouldCollect(discardPile, new Deck(), 0));
    }

    @Test
    public void shouldCollectForNullDeck()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        assertFalse(game.shouldCollect(discardPile, null, 0));
    }

    @Test
    public void shouldCollectForNoCardLeftInDeck()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        Deck deck = new Deck();
        deck.drawCard(Deck.NUM_CARDS);
        assertFalse(game.shouldCollect(discardPile, deck, 0));
    }

    @Test
    public void shouldCollectForWrongSeatNumber()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        Deck deck = new Deck();
        assertFalse(game.shouldCollect(discardPile, deck, 10));
    }

    @Test
    public void shouldCollectForNoCardLeft()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        Deck deck = new Deck();
        deck.drawCard(Deck.NUM_CARDS);
        assertTrue(game.shouldCollect(discardPile, deck, 3));
    }

    @Test
    public void shouldCollectForDiscardCardJack()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, Card.JACK));
        Deck deck = new Deck();
        assertTrue(game.shouldCollect(discardPile, deck, 3));
    }

    @Test
    public void shouldCollectForDiscardCardRegular()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, Card.ACE));
        Deck deck = new Deck();
        assertFalse(game.shouldCollect(discardPile, deck, 2));
    }

    @Test
    public void shouldCollectForDiscardCardSame()
    {
        Game game = new Game();

        Stack<Card> discardPile = new Stack<Card>();
        discardPile.push(Card.newCard(Card.Type.CLUBS, 2));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, 3));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, Card.ACE));
        discardPile.push(Card.newCard(Card.Type.DIAMONDS, Card.ACE));
        Deck deck = new Deck();
        assertTrue(game.shouldCollect(discardPile, deck, 1));
    }

    @Test
    public void gamePlay() {
        Bot[] bots = new Bot[4];
        bots[0] = new SuperDummyBot();
        bots[1] = new SuperDummyBot();
        bots[2] = new SuperDummyBot();
        bots[3] = new SuperDummyBot();
        Game game = new Game(bots);
        GameResult gameResult = game.executeGame();
        System.out.println(gameResult);
    }

}