package net.peakgames.pisti.utilities;

public class ShuffleHelper {

    /**
     * Exchanges any given two elements of an array
     *
     * @param theArray   Array
     * @param elementOne Element-1
     * @param elementTwo Element-2
     * @param <T>
     */
    private static <T> void exchange(T[] theArray, int elementOne, int elementTwo) {
        T swap = theArray[elementOne];
        theArray[elementOne] = theArray[elementTwo];
        theArray[elementTwo] = swap;
    }

    /**
     * Shuffles given array
     *
     * @param theArray is the Array which will be shuffled.
     */
    public static <T> void shuffle(T[] theArray) {
        int N = theArray.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N - i));
            //Exchange two elements.
            exchange(theArray, i, r);
        }
    }

}
