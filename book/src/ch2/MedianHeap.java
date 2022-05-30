package ch2;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://stephenjoel2k.medium.com/two-heaps-min-heap-max-heap-c3d32cbb671d
public class MedianHeap {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a - b);

    public static void main(String[] args) {
        MedianHeap mh = new MedianHeap();
//        mh.insert(5);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }
//        System.out.println();
//        mh.insert(4);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }
//        System.out.println();
//        mh.insert(3);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }
//        System.out.println();
//        mh.insert(2);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }
//        System.out.println();
//        mh.insert(1);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }
//        System.out.println();
//        System.out.println(mh.findMedian());
//
//        mh.remove(4);
//        System.out.println("maxheap");
//        for(int a : mh.maxHeap){
//            System.out.println(a);
//        }
//        System.out.println("minheap");
//        for(int a : mh.minHeap){
//            System.out.println(a);
//        }

        int[] nums = {4, 2, 1, 5, 6, 7, 1};
        var r = mh.medianSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(r));
        System.out.println("maxheap");
        for (int a : mh.maxHeap) {
            System.out.println(a);
        }
        System.out.println("minheap");
        for (int a : mh.minHeap) {
            System.out.println(a);
        }
    }

    public void insert(int x) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= x) {
            maxHeap.add(x);
        } else {
            minHeap.add(x);
        }
        balanceHeaps();
    }

    public void remove(int x) {
        if (x <= maxHeap.peek()) {
            maxHeap.remove(x);
        } else {
            minHeap.remove(x);
        }
        balanceHeaps();
    }

    public void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else
            return maxHeap.peek();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            insert(nums[i]);
            if (i - k + 1 >= 0) {
                result[i - k + 1] = findMedian();
                int elementToBeRemoved = nums[i - k + 1];
                remove(elementToBeRemoved);
            }
        }
        return result;
    }
}
