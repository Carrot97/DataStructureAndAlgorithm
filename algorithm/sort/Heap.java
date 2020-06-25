package sort;

import java.util.Arrays;

public class Heap {
    public static void sink(int[] data, int length, int i) {
        while (2*i+1 <= length-1) {
            int j = 2 * i + 1;
            if (j<length-1 && data[j] < data[j+1]) j++;
            if (data[i] > data[j]) break;
            Swap.swap(data, i ,j);
            i = j;
        }
    }

    public static void sort(int[] data) {
        int N = data.length;
        // 堆有序化
        for (int i=N/2-1; i>=0; i--) {
            sink(data, N, i);
        }
        // 逆序输出
        while (N>1) {
            Swap.swap(data, 0, N-1);
            N--;
            sink(data, N, 0);
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