/* *****************************************************************************
 *  Name: BruteCollinearPoints
 *  Date: Mar 10, 2019
 *  Description: Brute force method to detect collinear points
 **************************************************************************** */

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points

    // private static final int Nfactorial = 24;
    // N factorial is 24 for N = 4; the max array size needed
    // private LineSegment[] segments = new LineSegment[Nfactorial];

    private int segTracker = 0;
    //  we will not supply any input to BruteCollinearPoints that has 5 or more collinear point
    private int segmentSize = 10;
    private LineSegment[] segmentArr = new LineSegment[segmentSize];


    private void resizeSegmentArr() {
        LineSegment[] temp = new LineSegment[segmentArr.length * 2];
        for (int resizeTracker = 0; resizeTracker < segmentArr.length; resizeTracker++) {
            temp[resizeTracker] = segmentArr[resizeTracker];
        }
        segmentArr = temp;
    }

    private void generateCollinear(Point[] points, int size) {
        // generate all line segment possibilities!
        // prevent duplicate entries
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j)
                    continue;
                if (points[i].compareTo(points[j]) <= 0) // prevent back track like this: <-
                    continue;
                double pq = points[i].slopeTo(points[j]);

                for (int k = 0; k < size; k++) {
                    if (k == j || k == i) continue; // ignore the same point
                    if (points[i].compareTo(points[k]) <= 0) continue;
                    if (points[j].compareTo(points[k]) <= 0) continue;

                    double pr = points[i].slopeTo(points[k]);
                    if (pq != pr) continue; // not the same line

                    for (int l = 0; l < size; l++) {
                        if (l == k || l == j || l == i) continue;
                        // code combinations here!
//                        StdOut.print("i: " + i); // p
//                        StdOut.print(" j: " + j); // q
//                        StdOut.print(" k: " + k); // r
//                        StdOut.print(" l: " + l); // s
//                        StdOut.println(" end");

                        // set member i as the base point p;
                        if (points[i].compareTo(points[l]) <= 0) continue;
                        if (points[j].compareTo(points[l]) <= 0) continue;
                        if (points[k].compareTo(points[l]) <= 0) continue;
                        double ps = points[i].slopeTo(points[l]);
                        if (ps != pr) continue;

                        // otherwise, form a line segment with p->q or i/j
                        // added line segment
                        // StdOut.println("Added line segment number " + segTracker);

                        // resize segment tracker
                        if (segTracker == segmentArr.length) {
                            resizeSegmentArr();
                        }
                        System.out.println("i=" + i + ", l=" + l + "+" + points[i] + " " + points[l]);
                        segmentArr[segTracker++] = new LineSegment(points[i], points[l]);
                    }
                }
            }
        }
    }

    public BruteCollinearPoints(Point[] points) {
        // check for illegal arguments
        if (points == null) throw new IllegalArgumentException("Null values not alloved!");
        int size = points.length;
        for (int i = 0; i < size; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Null points are not allowed");
            for (int j = 0; j < size; j++) {
                if (points[j] == null) throw new IllegalArgumentException("Null points are not allowed");
                if (i != j) {
                    if (points[i].compareTo(points[j]) == 0)
                        throw new IllegalArgumentException("Do not supply same points multiple times!");
                }
            }
        }
        generateCollinear(points, points.length);

    }

    // the number of line segments
    public int numberOfSegments() {
        return segTracker;
    }

    // the line segments
    public LineSegment[] segments() {
        // StdOut.println(numberOfSegments());
        // StdOut.println(segmentArr);
        LineSegment[] segmentCloned = new LineSegment[segTracker];
        int cloneTracker = 0;
        for (LineSegment segment : segmentArr) {
            if (segment != null) {
                segmentCloned[cloneTracker] = segment;
                // StdOut.println(segment.toString());
                cloneTracker++;
            }
        }
        // ensure all values were copied over
        assert (cloneTracker == segTracker);

        return segmentCloned;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}