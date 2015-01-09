package net.peakgames.pisti;


public class PistiBootstrap {
    public static void main(String[] args) {

        //TODO ilkin
        int numberOfConcurrentGames;
        int numberOfTotalGames;
        String [] bots;

        Game game = new Game(null);
        GameResult gameResult = game.executeGame();
        System.out.println(gameResult);
    }
}
