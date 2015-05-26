package net.peakgames.pisti;


import net.peakgames.pisti.bot.Bot;
import net.peakgames.pisti.game.Game;
import net.peakgames.pisti.game.GameResult;
import net.peakgames.pisti.game.GameResultAggregator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Bootstrap the pisti game with given bots and threads and count.
 *
 * @author Peak Games
 */
public class PistiBootstrap {

    public static void main(final String[] args) throws Exception {
        if (args.length != 6) {
            printUsage();
            System.exit(1);
        }

        int numberOfConcurrentGames = readIntArgument(args, 0);
        int numberOfTotalGames = readIntArgument(args, 1);
        final GameResultAggregator gameResultAggregator = new GameResultAggregator();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfConcurrentGames);
        for (int i = 0; i < numberOfTotalGames; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    final Bot[] bots = new Bot[4];
                    try {
                        bots[0] = createBot(args[2]);
                        bots[1] = createBot(args[3]);
                        bots[2] = createBot(args[4]);
                        bots[3] = createBot(args[5]);
                        Game game = new Game(bots);
                        GameResult gameResult = game.executeGame();
                        System.out.println(gameResult);
                        gameResultAggregator.addGameResult(gameResult);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(gameResultAggregator);
    }

    private static Bot createBot(String klass) throws Exception {
        Class<Bot> aClass = (Class<Bot>) Class.forName(klass);
        return aClass.newInstance();
    }

    private static int readIntArgument(String[] args, int argIndex) {
        try {
            return Integer.parseInt(args[argIndex]);
        } catch (NumberFormatException e) {
            System.out.println("Argument " + args[argIndex] + " must be an integer.");
            System.exit(1);
            return 0;
        }
    }

    private static void printUsage() {
        //TODO Print the usage
        System.out.println("Usage : ");
    }
}
