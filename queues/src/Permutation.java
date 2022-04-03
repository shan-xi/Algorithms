import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (queue.size() < k) {
                queue.enqueue(s);
            } else {
                queue.dequeue();
                queue.enqueue(s);
            }
        }

        for (String s : queue) {
            System.out.println(s);
        }
    }
}
