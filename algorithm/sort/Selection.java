package sort;

import java.util.Arrays;

public class Selection {

    public static void sort(int[] data) {
        int N = data.length;
        for (int i=0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++)
                if (data[j] < data[min]) min = j;
            Swap.swap(data, i, min);
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
