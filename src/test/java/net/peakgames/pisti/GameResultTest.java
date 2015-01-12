package net.peakgames.pisti;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameResultTest  {

    @Test
    public void gameResultWithNullBots()
    {
        try {
            new GameResult(null);
        } catch (RuntimeException e) {
            assertEquals("Needs some bots to generate game result.", e.getMessage());
        }
    }

    @Test
    public void gameResultWithEmptyBots()
    {
        try {
            new GameResult(new BotWrapper[0]);
        } catch (RuntimeException e) {
            assertEquals("Needs some bots to generate game result.", e.getMessage());
        }
    }

    @Test
    public void isWinner()
    {
        BotWrapper[] bots = generateBots();

        GameResult gameResult = new GameResult(bots);

        assertTrue(gameResult.isWinner(bots[0]));
        assertFalse(gameResult.isWinner(bots[1]));
        assertTrue(gameResult.isWinner(bots[2]));
        assertFalse(gameResult.isWinner(bots[3]));

        System.out.println(gameResult);

    }

    private BotWrapper[] generateBots()
    {
        BotWrapper[] bots = new BotWrapper[4];

        bots[0] = new BotWrapper(0, new DummyBot());
        bots[1] = new BotWrapper(1, new DummyBot());
        bots[2] = new BotWrapper(2, new DummyBot());
        bots[3] = new BotWrapper(3, new DummyBot());

        bots[0].addScore(8);
        bots[1].addScore(2);
        bots[2].addScore(2);
        bots[2].addScore(3);
        bots[2].addScore(3);
        bots[3].addScore(7);
        return bots;
    }


}