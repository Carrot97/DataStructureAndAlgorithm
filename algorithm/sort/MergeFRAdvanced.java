package sort;

import java.util.Arrays;

public class MergeFRAdvanced extends MergeFromRoof{

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
