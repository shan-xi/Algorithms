package ch2;
// Java implementation of the approach

import java.util.Arrays;

public class CountingInversions {

    // Function to count the number of inversions
    // during the merge process
    private static int mergeAndCount(int[] arr, int l,
                                     int m, int r) {
        System.out.println(Arrays.toString(arr));
        System.out.printf("mergeAndCount(%d, %d, %d)\n", l, m, r);
        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l,
                m + 1);
        System.out.println("left "+Arrays.toString(left));

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1,
                r + 1);
        System.out.println("right "+Arrays.toString(right));

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
                System.out.printf("swap m=%d l=%d i=%d j=%d => %d\n", m, l ,i, j, (m + 1) - (l + i));
            }
            System.out.println(Arrays.toString(arr));
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        System.out.println(Arrays.toString(arr));
        return swaps;
    }

    // Merge sort function
    private static int mergeSortAndCount(int[] arr,
                                         int l, int r) {
        System.out.println(Arrays.toString(arr));
        System.out.printf("mergeSortAndCount(%d, %d)\n", l, r);

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left
            // subarray count + right subarray
            // count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);
            System.out.printf("Left %d \n",count);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);
            System.out.printf("Right %d \n",count);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
            System.out.printf("Merge %d \n",count);
        }

        return count;
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};

        System.out.println(
                mergeSortAndCount(arr, 0,
                        arr.length - 1));
        System.out.println(Arrays.toString(arr));
    }
}
// This code is contributed by Pradip Basak
