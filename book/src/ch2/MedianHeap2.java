package ch2;

public class MedianHeap2 {
    private MaxPQ<Integer> left;
    private MinPQ<Integer> right;
    private int L;
    private int R;

    MedianHeap2() {
        left = new MaxPQ<Integer>();
        right = new MinPQ<Integer>();
    }

    public static void main(String[] args) {
        MedianHeap2 mh = new MedianHeap2();
        mh.insert(1);
        mh.insert(2);
        mh.insert(3);
        mh.insert(4);
        mh.insert(5);
        System.out.println(mh.findMedian());
    }


    public double findMedian() {
        int L = left.size();
        int R = right.size();
        if (L == R)
            return ((double) left.max() + (double) right.min()) / 2;
        else if (L > R)
            return left.max();
        else
            return right.min();
    }

    public void insert(int key) {
        double median = findMedian();
        int L = left.size();
        int R = right.size();
        if (key <= median) {
            left.insert(key);
            if (L - R > 1)
                right.insert(left.delMax());
        } else {
            right.insert(key);
            if (R - L > 1)
                left.insert(right.delMin());
        }
    }

    public void removeMedian() {
        int L = left.size();
        int R = right.size();
        if (L > R) {
            left.delMax();
        } else {
            right.delMin();
        }
    }

}
