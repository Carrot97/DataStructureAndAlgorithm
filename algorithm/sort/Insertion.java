package sort;

import java.util.Arrays;

public class Insertion {

    public static void sort(int[] data, int lo, int hi) {
        for (int i=lo; i <= hi; i++) {
            int j = i;
            while ((j > 0) && (data[j-1] > data[i])) j--;
            // 集体右移操作
            if (j != i) {
                int tmp = data[i];
                Swap.backward(data, j, i - 1, 1, 1);
                data[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(data));

        sort(data, 0, data.length-1);
        System.out.println(Arrays.toString(data));
    }
}
