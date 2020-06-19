package sort;

import java.util.Arrays;

public class Quick {
    public static void sort(int[] data, int lo, int hi) {
        // 终止递归条件
        if (hi <= lo) return;
        // 划分操作：将数组划分为三部分：j位为元素V，j前为小于v的元素，j后为大于v的元素
        int j = partition(data, lo, hi);
        sort(data, lo, j-1);
        sort(data, j+1, hi);
    }

    public static int partition(int[] data, int lo, int hi) {
        int i = lo, j = hi + 1;
        while(true) {
            while (data[++i] < data[lo]) if (hi == i) break;
            while (data[lo] < data[--j]) if (j == lo) break;
            if (j <= i) break;
            Swap.swap(data, i, j);
        }
        Swap.swap(data, lo, j);
        return j;
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 10);
        }
        System.out.println(Arrays.toString(data));
        sort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
    }
}
