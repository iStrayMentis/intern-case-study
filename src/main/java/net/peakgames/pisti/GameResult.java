package net.peakgames.pisti;


import java.util.Arrays;
import java.util.Comparator;

public class GameResult {

    private BotWrapper[] bots;
    private final int winnerScore;

    public GameResult(BotWrapper[] bots)
    {
        if (bots == null || bots.length <= 0) {
            throw new RuntimeException("Needs some bots to generate game result.");
        }

        this.bots = bots.clone();

        Arrays.sort(this.bots, new Comparator<BotWrapper>(){
            @Override
            public int compare(BotWrapper b1, BotWrapper b2){
                return  b2.getScore() - b1.getScore();
            }
        } );

        this.winnerScore = this.bots[0].getScore();
    }

    boolean isWinner(BotWrapper bot)
    {
        if(bot.getScore() >= this.winnerScore) {
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (BotWrapper bot : bots) {
            sb.append("\n");
            sb.append(bot.scoreWithBotName());
            if(isWinner(bot)) {
                sb.append(" (WINNER)");
            }
        }
        return sb.toString();
    }
}
