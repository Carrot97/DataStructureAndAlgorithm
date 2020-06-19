package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickAdvanced {
    public static void sort(int[] data) {
        int N = data.length;
        // 对于小规模数组排序，切换到插入排序。书2.3.3.1
        if (N < 15) {
            Insertion.sort(data, 0, N-1);
            return;
        }

        // 为减小最差情况的出现的概率，在排序之前先将数组随机洗牌。书2.3.1.3
        Swap.shuffle(data, 0, N-1);
        sort(data, 0, N-1);
    }

    public static void sort(int[] data, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(data, lo, hi);
        sort(data, lo, j-1);
        sort(data, j+1, hi);
    }

    public static int partition(int[] data, int lo, int hi) {
        int i = lo, j = hi + 1;
        // 三取样切分：通过对数组进行三次随机采样，选择其中最小的元素作为切分元素v。书2.3.3.2
        sample3(data, lo, hi);
        while(true) {
            while (data[++i] < data[lo]) if (hi == i) break;
            while (data[lo] < data[--j]) ;  // 切分元素V可作为左哨兵。书2.3.1.2
            if (j <= i) break;
            Swap.swap(data, i, j);
        }
        Swap.swap(data, lo, j);
        return j;
    }

    public static void sample3(int[] data, int lo, int hi) {
        int N = hi - lo + 1;
        Random rand = new Random();
        int[] index = new int[3];
        // 随机选择三个索引值
        for (int i=0; i < index.length; i++)
            index[i] = rand.nextInt(N) + lo;
        // 将索引按照对应的值排序
        if (data[index[0]] > data[index[1]]) Swap.swap(index, 0, 1);
        if (data[index[0]] > data[index[2]]) Swap.swap(index, 0, 2);
        if (data[index[1]] > data[index[2]]) Swap.swap(index, 1, 2);
        Swap.swap(data, lo, index[1]);
        Swap.swap(data, hi, index[2]);  // 将刚刚发现的大于等于切分元素v的元素放在数组末尾作为有哨兵。书2.3.3.2后半部分
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
