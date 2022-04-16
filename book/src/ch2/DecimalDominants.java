package ch2;
//https://stackoverflow.com/questions/9599559/decimal-dominant-number-in-an-array

import java.util.Arrays;

/**
 * Question 3
 * Decimal dominants. Given an array with nn keys, design an algorithm to find all values that occur more than n/10n/10 times. The expected running time of your algorithm should be linear.
 * Hint: determine the (n/10)^{th}(n/10)th largest key using quickselect and check if it occurs more than n/10n/10 times.
 * <p>
 * Alternate solution hint: use 9 counters.
 */
public class DecimalDominants {
    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        System.out.println("entry->"+lo+" "+hi);
        if (lo >= hi) return;
        int lt = lo, gt = hi;
        Comparable v = arr[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) swap(arr, lt++, i++);
            else if (cmp > 0) swap(arr, i, gt--);
            else i++;
        }
        System.out.println(lt+" "+gt);
        System.out.println(Arrays.toString(arr));

        int times = gt - lt + 1;
        if (times > arr.length / 10) System.out.printf("%s repeats %d times\n", v, times);

        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 1, 2, 1, 2, 1, 4, 3, 2, 4, 2, 3, 5, 6, 7, 8, 9, 3, 2, 4, 5, 2, 3, 6, 7, 8, 3, 2, 5};
        System.out.println("print numbers which repeat more then " + arr.length / 10);
        sort(arr);
    }
}