package ch2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
/**
 *  The {@code Multiway} class provides a client for reading in several
 *  sorted text files and merging them together into a single sorted
 *  text stream.
 *  This implementation uses a {@link IndexMinPQ} to perform the multiway
 *  merge.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class Multiway {

    // This class should not be instantiated.
    private Multiway() { }

    public static void merge(In[] streams)
    {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (int i = 0; i < N; i++)
            if (!streams[i].isEmpty()){
                String next = streams[i].readString();
                System.out.println(next);
                pq.insert(i, next);
            }
        while (!pq.isEmpty())
        {
            StdOut.print(pq.minKey() + " " +pq.minIndex()+" ");
            int i = pq.delMin();
            System.out.print("i="+i);
            if (!streams[i].isEmpty()){
                String next = streams[i].readString();
                System.out.printf(" => i=%d %s", i, next);
                pq.insert(i, next);
                StdOut.println();
            }
        }
        StdOut.println();
    }
    public static void main(String[] args)
    {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++)
            streams[i] = new In(args[i]);
        merge(streams);
    }
}