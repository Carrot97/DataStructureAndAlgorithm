package sort;

import java.util.Arrays;

public class MergeFromRoot {
    protected static int[] aux;

    public static void sort(int[] data) {
        aux = new int[data.length];
        sort(data, 0, data.length-1);
    }

    public static void sort(int[] data, int lo, int hi) {
        int N = hi - lo + 1;
        for (int i=1; i < N; i <<= 1)
            for (int j=0; j < N; j += 2*i)
                merge(data, j+lo, j+lo+i-1, Math.min(lo+N-1, j+lo+2*i-1));
    }

    public static void merge(int[] data, int lo, int mid, int hi) {
        int i = lo, j = mid+1;
        // 复制数组
        for (int k=lo; k <= hi; k++) aux[k] = data[k];
        // 排序
        for (int k=lo; k <= hi; k++) {
            if (i > mid) data[k] = aux[j++];
            else if (j > hi) data[k] = aux[i++];
            else if (aux[j] < aux[i]) data[k] = aux[j++];
            else  data[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(data));

        sort(data);
        System.out.println(Arrays.toString(data));
    }
}
