package unionFind;

public class QuickUnion extends QuickFind {

    public QuickUnion(int count) { super(count); }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int qId = find(q);
        int pId = find(p);
        if (pId == qId) return;
        id[qId] = pId;
        count--;
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

        QuickUnion uf = new QuickUnion(10);
        System.out.println(uf.toString());
        for (int i=0; i < dataSet.length; i++) {
            int p = dataSet[i][0];
            int q = dataSet[i][1];
            uf.union(p, q);
            System.out.println(uf.toString());
        }
        System.out.println("There are " + uf.count() + " components.");
    }

}
