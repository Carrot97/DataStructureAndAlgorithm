package sort;

import java.lang.Math;
import java.util.Arrays;

public class Bubble {

    public static int[] sort(int[] data) {
        // 提前终止控制
        boolean isDone = true;
        for (int i=0; isDone; i++) {
            isDone = false;
            for (int j=0; j<data.length-i-1; j++) {
                if (data[j] > data[j+1]) {
                    // 类包装swap
//                    MyInteger a = new MyInteger(data[j]);
//                    MyInteger b = new MyInteger(data[j+1]);
//                    Swap.swap(a, b);
//                    data[j] = a.getValue();
//                    data[j+1] = b.getValue();
                    // 直接swap
                    int tmp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = tmp;

                    isDone = true;
                }
            }
        }

        return data;
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(data));

        int[] dataSorted = sort(data);
        System.out.println(Arrays.toString(dataSorted));
    }

}
