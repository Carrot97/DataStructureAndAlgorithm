package sort;

import java.util.Arrays;

public class TopM {
    private int M = 0;
    private int[] topM;

    public TopM(int M) {
        this.M = M;
        topM = new int[M];
    }

    public void sink() {
        int i = 0;
        while (2*i+1 <= M-1) {
            int j = 2 * i + 1;
            if (j<M-1 && topM[j] > topM[j+1]) j++;
            if (topM[i] < topM[j]) break;
            Swap.swap(topM, i ,j);
            i = j;
        }
    }

    public int[] sortM(int[] data) {
        for (int datum : data) {
            if (datum > topM[0]) {
                topM[0] = datum;
                sink();
            }
        }
        while (M > 1) {
            Swap.swap(topM, 0, M-1);
            M--;
            sink();
        }
        return topM;
    }

    public static void main(String[] args) {
        int[] data = new int[50];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(data));

        TopM tm = new TopM(10);
        int[] dataTopM = tm.sortM(data);
        System.out.println(Arrays.toString(dataTopM));
    }
}
