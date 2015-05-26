package net.peakgames.pisti.game;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Collects and aggregates game results.
 *
 * @see net.peakgames.pisti.game.GameResult
 *
 * @author Peak Games
 */
public class GameResultAggregator {

    private Map<String, AtomicInteger> results = new HashMap<String, AtomicInteger>();

    public synchronized void addGameResult(GameResult gameResult)
    {
        String winnerName = gameResult.getWinnerName();
        AtomicInteger count = results.get(winnerName);
        if (count == null) {
            count = new AtomicInteger();
            results.put (winnerName, count);
        }
        count.incrementAndGet();
    }

    @Override
    public String toString() {
        return results.toString();
    }
}
