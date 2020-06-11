package unionFind;

import java.util.Arrays;

public class QuickFind {
    protected int[] id;
    protected int count;

    public QuickFind(int count) {
        this.count = count;
        id = new int[count];
        for (int i=0; i < count; i++) id[i] = i;
    }

    public int count() { return count; }

    public int find(int p) { return id[p]; }

    public void union(int p, int q) {
        int qId = find(q);
        int pId = find(p);
        if (pId == qId) return;
        for (int i=0; i < id.length; i++)
            if (id[i] == qId) id[i] = pId;
        count--;
    }

    public boolean isConnected(int p, int q) { return find(p) == find(q); }

    @Override
    public String toString() { return Arrays.toString(id); }

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

        QuickFind uf = new QuickFind(10);
        System.out.println(uf.toString());
        for (int i=0; i < dataSet.length; i++) {
            int p = dataSet[i][0];
            int q = dataSet[i][1];
            uf.union(p, q);
            System.out.println(uf.toString());
        }
        System.out.println(uf.isConnected(1,7));
        System.out.println("There are " + uf.count() + " components.");
    }
}
