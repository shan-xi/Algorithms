import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    final private WeightedQuickUnionUF grid;
    final private WeightedQuickUnionUF full;
    final private int n;
    final private int top;
    final private int bottom;
    private final boolean[] openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(n * n + 2);
        full = new WeightedQuickUnionUF(n * n + 1);
        this.n = n;
        top = getOneDimensionArrayIndex(n, n) + 1;
        bottom = getOneDimensionArrayIndex(n, n) + 2;
        openSites = new boolean[n * n];
    }

    private int getOneDimensionArrayIndex(int row, int col) {
        checkRange(row, col);
        return (n * (row - 1) + col) - 1;
    }

    private boolean isValidIndex(int row, int col) {
        return row > 0 && col > 0 && row <= n && col <= n;
    }

    private void checkRange(int row, int col) {
        if (!isValidIndex(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRange(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int idx = getOneDimensionArrayIndex(row, col);
        openSites[idx] = true;


        if (row == 1) {
            grid.union(top, idx);
            full.union(top, idx);
        }

        if (row == n) {
            grid.union(bottom, idx);
        }

        // up neighboring
        if (isValidIndex(row - 1, col) && isOpen(row - 1, col)) {
            grid.union(getOneDimensionArrayIndex(row - 1, col), idx);
            full.union(getOneDimensionArrayIndex(row - 1, col), idx);
        }

        // right neighboring
        if (isValidIndex(row, col + 1) && isOpen(row, col + 1)) {
            grid.union(getOneDimensionArrayIndex(row, col + 1), idx);
            full.union(getOneDimensionArrayIndex(row, col + 1), idx);
        }

        // down neighboring
        if (isValidIndex(row + 1, col) && isOpen(row + 1, col)) {
            grid.union(getOneDimensionArrayIndex(row + 1, col), idx);
            full.union(getOneDimensionArrayIndex(row + 1, col), idx);
        }

        // left neighboring
        if (isValidIndex(row, col - 1) && isOpen(row, col - 1)) {
            grid.union(getOneDimensionArrayIndex(row, col - 1), idx);
            full.union(getOneDimensionArrayIndex(row, col - 1), idx);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSites[getOneDimensionArrayIndex(row, col)];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int idx = getOneDimensionArrayIndex(row, col);
        return full.connected(idx, top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int opened = 0;
        for (boolean s : openSites) {
            if (s) {
                opened++;
            }
        }
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}