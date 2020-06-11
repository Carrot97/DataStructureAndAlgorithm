package sort;

import java.util.Arrays;

public class Shell {
    public static void sort(int[] data) {
        int N = data.length;
        int h = N / 2;
        while (h > 0) {
            for (int i=h; i < N; i++) {
                int j = i;
                while ((j >= h) && (data[j-h] > data[i])) j -= h;
                if (j != i) {
                    int tmp = data[i];
                    Swap.backward(data, j, i - h, h, h);
                    data[j] = tmp;
                }
            }
            h /= 2;
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
