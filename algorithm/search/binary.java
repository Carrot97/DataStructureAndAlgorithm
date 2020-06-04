package search;

import sort.Bubble;
import java.util.Arrays;

public class binary {
    public static int search(int[] array, int data) {
        int lo = 0;
        int hi = array.length;
        int min = 0;
        while (lo < hi) {
            min = (lo + hi) >> 1;
            if (data < array[min]) hi = min;
            else if (array[min] < data) lo = min + 1;
            else return min;
        }
        System.out.println("not found");
        return -1;
    }

    public static int rank(int[] array, int data) {
        int count = 1;
        int idx = search(array, data);
        if (idx != -1) {
            for (int i = 0; i < idx; i++) {
                if (array[i] != array[i + 1] && array[i + 1] != data)
                    count++;
            }
            return count;
        }
        else return -1;

    }

    public static int count(int[] array, int data) {
        int counter = 1;
        int idx = search(array, data);
        if (idx != -1) {
            for (int i = idx - 1; array[i] == data; i--)
                counter++;
            for (int i = idx + 1; array[i] == data; i++)
                counter++;
            return counter;
        }
        else return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[20];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 10);
        }
//        data[0] = 233;
        int[] dataSorted = Bubble.sort(data);
        System.out.println(Arrays.toString(dataSorted));
        System.out.println(search(dataSorted, 2));
        System.out.println(rank(dataSorted,2));
        System.out.println(count(dataSorted, 2));
    }
}
