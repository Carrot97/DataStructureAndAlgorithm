package unionFind;
import java.util.Arrays;

public class WeightedQuickUnion extends QuickUnion {
    protected int[] th;

    public WeightedQuickUnion(int count) {
        super(count);
        th = new int[count];
        for (int i=0; i < count; i++) th[i] = 1;
    }

    @Override
    public void union(int p, int q) {
        int qId = find(q);
        int pId = find(p);
        if (pId == qId) return;
        if (th[pId] < th[qId]) {
            id[pId] = qId;
            th[qId] += th[pId];
        }
        else {
            id[qId] = pId;
            th[pId] += th[qId];
        }
        count--;
    }

    public String printTH() { return Arrays.toString(th); }

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

        WeightedQuickUnion uf = new WeightedQuickUnion(10);
        System.out.println(uf.toString());
        for (int i=0; i < dataSet.length; i++) {
            int p = dataSet[i][0];
            int q = dataSet[i][1];
            uf.union(p, q);
            System.out.println(uf.toString());
            System.out.println("Subtree height is " + uf.printTH());
        }
//        System.out.println("Subtree height is " + uf.printTH());
        System.out.println("There are " + uf.count() + " components.");
    }
}
