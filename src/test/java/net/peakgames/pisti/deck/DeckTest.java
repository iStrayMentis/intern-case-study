package net.peakgames.pisti.deck;

import net.peakgames.pisti.deck.Card;
import net.peakgames.pisti.deck.Deck;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeckTest {


    @org.junit.Test
    public void testDrawCard() throws Exception {
        draw(52, 1,false);
        draw(13, 4,false);
        draw(52, 1,true);
        draw(13, 4,true);

    }

    private void draw(int numberOfIterations, int numCardsToDrawInEachIteration, boolean shouldShuffle) {
        Deck deck = new Deck();

        if (shouldShuffle) {
            //You should shuffle deck for randomisation!
            deck.shuffle();
        }

        assertEquals(52,deck.numberOfCardsLeftInDeck());

        HashSet<Card> cardDrawn = new HashSet<Card>();
        for (int i = 0; i < numberOfIterations; i++) {
            List<Card> list = deck.drawCard(numCardsToDrawInEachIteration);
            for (Card c : list) {
                boolean alreadyExists = cardDrawn.add(c);
                assertTrue("A drawn card should not be appeared again.",alreadyExists);

            }
            assertEquals("Drawn cards has wrong count!", numCardsToDrawInEachIteration, list.size());
        }


        assertTrue("Deck should be empty", deck.isEmpty());
        assertEquals(0,deck.numberOfCardsLeftInDeck());

        try {
            //Since we draw all 52 cards; next draw should cause problem!
            deck.drawCard(numCardsToDrawInEachIteration);
            //Code should not follow here!
            Assert.fail();
        } catch (RuntimeException e) {
            assertEquals("Not enough cards left in deck!", e.getMessage());
        }
    }


}