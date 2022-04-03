import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int N;
    private int lastLoc;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        N = 0;
        lastLoc = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = q[i];
        }
        q = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (N == q.length) {
            resize(2 * q.length);
        }
        q[N++] = item;
        lastLoc = N;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(N);
        Item val = q[rand];
        if (rand != N - 1) {
            q[rand] = q[N - 1];
        }
        q[N - 1] = null;
        N--;
        if (N > 0 && N <= q.length / 4) {
            resize(q.length / 2);
        }
        return val;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return q[StdRandom.uniform(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private int randLoc = 0;
        private int copySize = N;
        private Item[] copy = (Item[]) new Object[copySize];

        private RandomIterator() {
            for (int i = 0; i < copySize; i++) {
                copy[i] = q[i];
            }
        }

        @Override
        public boolean hasNext() {
            return copySize > 0;
        }

        @Override
        public Item next() {
            if (copySize == 0) {
                throw new NoSuchElementException();
            }
            randLoc = StdRandom.uniform(copySize);
            Item currentItem = copy[randLoc];
            if (randLoc != copySize - 1) {
                copy[randLoc] = copy[copySize - 1];
            }
            copy[copySize - 1] = null;
            copySize--;
            return currentItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        String item = rq.dequeue();
        System.out.println(item);
    }
}
