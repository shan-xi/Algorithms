package ch1;

public class SearchInBitonicArray {
    private static int search(int[] a, int target) {
        int maxIndex = findMaxElement(a);
        int targetIndex = orderAgnosticBinarySearch(a, 0, maxIndex, target);
        if (targetIndex != -1) {
            return targetIndex;
        }
        return orderAgnosticBinarySearch(a, maxIndex + 1, a.length - 1, target);
    }

    private static int orderAgnosticBinarySearch(int[] a, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (target == a[mid]) {
            return mid;
        } else if ((a[start] < a[end] && target < a[mid]) || (a[start] > a[end] && target > a[mid])) {
            return orderAgnosticBinarySearch(a, start, mid - 1, target);
        } else {
            return orderAgnosticBinarySearch(a, mid + 1, end, target);
        }
    }

    private static int findMaxElement(int[] a) {
        int n = a.length;
        int start = 0;
        int end = n - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if ((mid == 0 || a[mid] > a[mid - 1]) && (mid == n - 1 || a[mid] > a[mid + 1])) {
                return mid;
            } else if (a[mid] < a[mid - 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {2, 4, 8, 10, 7, 6, 1};
        int target = 6;
        System.out.println(
                search(a, target)
        );
    }
}
