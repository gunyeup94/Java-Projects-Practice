public class UnionFind {


    private int[] set;
    private int[] init;
    private int numOfDisjoint;

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        set = new int[N];
        init = new int[N];
        for (int i = 0; i < N; i++) {
            set[i] = i;
            init[i] = -1;
        }

        numOfDisjoint = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v);
        return -init[root];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (init[v] < 0) {
            return init[v];
        } else {
            return init[v];
        }
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || set.length < v) {
            throw new IllegalArgumentException("Invalid vertices");
        }
        while (init[v] >= 0) {
            v = init[v];
        }
        return v;
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        int v11 = find(v1);
        int v22 = find(v2);
        if (v11 == v22) {
            return;
        }
        if (init[v11] <= init[v22]) {
            int temp = init[v11];
            init[v11] = v22;
            init[v22] = init[v22] + temp;
        } else {
            int temp = init[v22];
            init[v22] = v11;
            init[v11] = init[v11] + temp;
        }
        numOfDisjoint--;
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        uf.union(0,1);
        uf.union(3,2);
        uf.union(2,1);
        System.out.println(uf.parent(3));
        System.out.println(uf.sizeOf(3));
        System.out.println(uf.connected(1,2));
        System.out.println(uf.find(3));
    }
}
