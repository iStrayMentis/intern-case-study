package net.peakgames.pisti;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PistiBootstrap {

    public static void main(String[] args) throws Exception {
        if (args.length != 6) {
            printUsage();
            System.exit(1);
        }

        int numberOfConcurrentGames = readIntArgument(args, 0);
        int numberOfTotalGames = readIntArgument(args, 1);
        Bot[] bots = new Bot[4];
        bots[0] = createBot(args[2]);
        bots[1] = createBot(args[3]);
        bots[2] = createBot(args[4]);
        bots[3] = createBot(args[5]);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfConcurrentGames);
        for (int i = 0; i < numberOfTotalGames; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Game game = new Game(null);
                    GameResult gameResult = game.executeGame();
                    System.out.println(gameResult);
                }
            });
        }
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
        //TODO ilkin
        System.out.println("Usage : ");
    }
}
