package net.peakgames.pisti.game;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Calculates the game result.
 *
 * @see BotDecorator
 *
 * @author Peak Games
 */
public class GameResult {

    private BotDecorator[] bots;
    private final int winnerScore;

    /**
     * Calculates the game result.
     *
     * @param bots
     */
    public GameResult(BotDecorator[] bots)
    {
        if (bots == null || bots.length <= 0) {
            throw new RuntimeException("Needs some bots to generate game result.");
        }

        this.bots = bots.clone();

        Arrays.sort(this.bots, new Comparator<BotDecorator>(){
            @Override
            public int compare(BotDecorator b1, BotDecorator b2){
                return  b2.getScore() - b1.getScore();
            }
        } );

        this.winnerScore = this.bots[0].getScore();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (BotDecorator bot : bots) {
            sb.append("\n");
            sb.append(bot.scoreWithBotName());
            if(isWinner(bot)) {
                sb.append(" (WINNER)");
            }
        }
        return sb.toString();
    }

    public String getWinnerName()
    {
        for(BotDecorator wrapper : bots) {
            if (isWinner(wrapper)) {
                return wrapper.getBotName();
            }
        }
        throw new IllegalStateException("There should one winner at least.");
    }

    boolean isWinner(BotDecorator bot)
    {
        if(bot.getScore() >= this.winnerScore) {
            return true;
        }

        else {
            return false;
        }
    }

}
