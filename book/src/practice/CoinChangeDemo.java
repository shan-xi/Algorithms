package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class CoinChangeDemo {

    static <R> R wrapper(BiFunction<Integer, Integer[], R> cc, Integer a, Integer[] b) {
        final long startTime = System.nanoTime();
        R r = cc.apply(a, b);
        System.out.println(r);
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration);
        return r;
    }

    static class CoinChangeWays implements BiFunction<Integer, Integer[], Integer> {

        @Override
        public Integer apply(Integer n, Integer[] coins) {

            int[] ways = new int[n + 1];
            ways[0] = 1;

            for (int i = 0; i < coins.length; i++) {
//                System.out.println("coin= " + coins[i]);
                for (int j = 1; j <= n; j++) {
                    if (j >= coins[i]) {
                        ways[j] = ways[j - coins[i]] + ways[j];
                    }
//                    System.out.println(Arrays.toString(ways));
                }
            }
//            System.out.println(Arrays.toString(ways));
            return ways[n];
        }
    }

    // https://hellokoding.com/coin-change-problem-find-the-minimum-number-of-coins-of-making-change/
    static class CoinChangeMinimum implements BiFunction<Integer, Integer[], Integer> {

        @Override
        public Integer apply(Integer n, Integer[] coins) {

            int[] minNoOfCoins = new int[n + 1];
            Arrays.fill(minNoOfCoins, n + 1);
            minNoOfCoins[0] = 0;

//            System.out.println(Arrays.toString(minNoOfCoins));
            for (int i = 0; i < coins.length; i++) {
//                System.out.println("coin= " + coins[i]);
                for (int j = 1; j <= n; j++) {
                    if (j >= coins[i]) {
                        minNoOfCoins[j] = Math.min(minNoOfCoins[j], minNoOfCoins[j - coins[i]] + 1);
                    }
//                    System.out.println(Arrays.toString(minNoOfCoins));
                }
            }
//            System.out.println(Arrays.toString(minNoOfCoins));
            return minNoOfCoins[n] > n ? -1 : minNoOfCoins[n];
        }
    }

    public static void main(String[] args) {
        int n = 12;
        Integer[] coins = {1, 5, 10};
        wrapper(new CoinChangeMinimum(), n, coins);
        wrapper(new CoinChangeWays(), n, coins);
//        n = 10;
//        coins = new Integer[]{1, 5, 10};
//        wrapper(new CoinChangeMinimum(), n, coins);
//        n = 8;
//        coins = new Integer[]{1, 5, 10};
//        wrapper(new CoinChangeMinimum(), n, coins);
//        n = 3;
//        coins = new Integer[]{2, 3, 1};
//        wrapper(new CoinChangeMinimum(), n, coins);
//
//        n = 30;
//        coins = new Integer[]{25, 10, 5};
//        wrapper(new CoinChangeMinimum(), n, coins);

    }
}
