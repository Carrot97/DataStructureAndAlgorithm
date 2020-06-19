package sort;

import java.util.Arrays;

// 对存在大量重复的数组排序耗时可达到 线性 级别
public class Quick3way {
    public static void sort(int[] data) {

        Swap.shuffle(data, 0, data.length-1);
        sort(data, 0, data.length-1);
    }

    public static void sort(int[] data, int lo, int hi) {
        if (hi <= lo) return;

        // 三段式，将数组按顺序分为：大于v的部分、等于v的部分和小于v的部分
        int lt = lo, i = lo + 1, gt = hi, v = data[lo];
        while (i <= gt) {
            if (data[i] < v) Swap.swap(data, i++, lt++);
            else if (v < data[i]) Swap.swap(data, i, gt--);
            else i++;
        }
        sort(data, lo, lt-1);
        sort(data, gt+1, hi);
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 10);
        }
        System.out.println(Arrays.toString(data));
        sort(data);
        System.out.println(Arrays.toString(data));
    }
}
