package sort;

import java.util.Arrays;

public class MergeFRAdvanced {
    protected static int[] aux;

    public static void sort(int[] data) {
        aux = new int[data.length];
        sort(data, 0, data.length-1);
    }

    public static void sort(int[] data, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            // 对小规模子数组使用插入排序
            if (mid-lo+1 > 4) sort(data, lo, mid);
            else Insertion.sort(data, lo, mid);
            if (hi-mid > 4) sort(data, mid+1, hi);
            else Insertion.sort(data, mid+1, hi);
            // 测试数组是否已经有序
            if (data[mid] > data[mid+1])
            merge(data, lo, mid, hi);
        }
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
