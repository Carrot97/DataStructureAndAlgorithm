package unionFind;

public class PathCompressedQU extends QuickUnion {
    public PathCompressedQU(int count) { super(count); }

    @Override
    public int find(int p) {
        int root = super.find(p);
        while (p != root) {
            int tmp = p;
            p = id[p];
            id[tmp] = root;
        }
        return root;
    }

    public static void main(String[] args) {
        int[][] dataSet = { {4, 3},
                {3, 8},
                {6, 5},
                {9, 4},
                {2, 1},
                {8, 9},
                {5, 0},
                {7, 2},
                {6, 1}};

        PathCompressedQU uf = new PathCompressedQU(10);
        for (int i=0; i < dataSet.length; i++) {
            int p = dataSet[i][0];
            int q = dataSet[i][1];
            uf.union(p, q);
            System.out.println(uf.toString());
        }

        System.out.println("There are " + uf.count() + " components.");
    }
}
