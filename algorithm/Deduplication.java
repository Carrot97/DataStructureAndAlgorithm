import java.util.Arrays;

import sort.Bubble;
import sort.Bubble.*;

public class Deduplication {
    private static int find(int data, int[] array, int hi) {
        for (int i=0; i<hi; i++) {
            if (array[i] == data) return i;
        }
        return -1;
    }

    private static void exchange(int[] array, int i, int deleteNum) {
        int tmp = array[i];
        array[i] = array[array.length - deleteNum -1];
        array[array.length - deleteNum - 1] = tmp;
    }

    public static int[] disorderlyDeduplicate(int[] array) {
        // 思路：将重复元素置于数组末尾，最后将不重复部分复制到新数组中
        int oldSize = array.length;
        int deleteNum = 0;
        for (int i=1, j=1; i<oldSize; i++) {
            if (find(array[j], array, j) == -1) j++;
            else {
                exchange(array, j, deleteNum);
                deleteNum++;
            }
        }
        System.out.println(Arrays.toString(array));
        int[] newArray = new int[oldSize-deleteNum];
        System.arraycopy(array, 0, newArray, 0, oldSize-deleteNum);
        array = null;
        return newArray;
    }

    public static int[] orderedDeduplicate(int[] array) {
        int i=0, tmp;
        for (int j=1; j<array.length; j++) {
            if (array[i] != array[j]) {
                if (j - i > 1) {
                    tmp = array[i + 1];
                    array[i + 1] = array[j];
                    array[j] = tmp;
                }
                i++;
            }
        }
        int[] newArray = new int[i+1];
        System.arraycopy(array, 0, newArray, 0, i+1);
        array = null;
        return newArray;
    }

    public static void main(String[] args) {
        int[] data = new int[10];
        for(int i=0; i<data.length; i++){
            data[i] = (int) (Math.random() * 10);
        }
        System.out.println(Arrays.toString(data));

        int[] data_ddp = disorderlyDeduplicate(data);
        System.out.println(Arrays.toString(data_ddp));

        int[] dataSorted = Bubble.sort(data);
        System.out.println(Arrays.toString(dataSorted));
        int[] data_odp = orderedDeduplicate(dataSorted);
        System.out.println(Arrays.toString(data_odp));

    }
}
