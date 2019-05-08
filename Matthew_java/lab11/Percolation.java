import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int gridsize;
    private WeightedQuickUnionUF wqf;
    private int opensize;
    private int top = 0;
    private int bottom;

    /* Creates an N-by-N grid with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Negative grid does not exist");
        }
        grid = new boolean[N][N];
        gridsize = N;
        wqf = new WeightedQuickUnionUF((N * N + 2));
        bottom = N * N + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    /* Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (!valid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        if (row == 0) {
            wqf.union(xyTo1D(row, col), top);
        }
        if (row == gridsize - 1) {
            wqf.union(xyTo1D(row, col), bottom);
        }

        if (col > 0 && isOpen(row, col - 1)) { //left
            wqf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }

        if (col < gridsize - 1 && isOpen(row, col + 1)) { //right
            wqf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }

        if (row > 0 && isOpen(row - 1, col)) {  //up
            wqf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }

        if (row < gridsize - 1 && isOpen(row + 1, col)) {
            wqf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        opensize++;
    }

    /* Returns true if the site at (row, col) is open. */
    public boolean isOpen(int row, int col) {
        if (!valid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        return grid[row][col];
    }

    /* Returns true if the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        if (!valid(row, col)) {
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        if (isOpen(row, col)) {
            int siteNum = xyTo1D(row, col);
            for (int i = 0; i < gridsize; i++) {
                if (wqf.connected(siteNum, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the number of open sites. */
    public int numberOfOpenSites() {
        return opensize;
    }

    /* Returns true if the system percolates. */
    public boolean percolates() {
        return wqf.connected(top, bottom);
    }

    /* Converts row and column coordinates into a number. This will be helpful
       when trying to tie in the disjoint sets into our NxN grid of sites. */
    private int xyTo1D(int row, int col) {
        return row * gridsize + col;
    }
    /* Returns true if (row, col) site exists in the NxN grid of sites.
       Otherwise, return false. */
    private boolean valid(int row, int col) {
        if (row < 0 || row >= gridsize || col < 0 || col >= gridsize) {
            return false;
        } else {
            return true;
        }
    }
}
