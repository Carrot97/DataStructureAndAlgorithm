import java.util.Arrays;

public class Heap<Key extends Comparable<Key>> {
    private Key[] heap;
    private int N = 0;

    public Heap(int maxN) { heap = (Key[]) new Comparable[maxN + 1]; }  // 建立泛型数组

    public boolean isEmpty() { return N == 0; }

    public int length() { return N; }

    public void swap(int i, int j) {
        Key tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void insert(Key newKey) {
        heap[++N] = newKey;
        swim(N);
    }

    public Key delMax() {
        Key max = heap[1];
        swap(1, N--);
        heap[N+1] = null;
        sink(1);
        return max;
    }

    public void swim(int i) {
        while ((i > 1) && (heap[i].compareTo(heap[i/2]) > 0)) {
            swap(i, i/2);
            i /= 2;
        }
    }

    public void sink(int i) {
        while (2*i <= N) {
            int j = 2 * i;
            if (j < N && heap[j].compareTo(heap[j+1]) < 0) j++;
            if (heap[i].compareTo(heap[j]) > 0) break;
            swap(i, j);
            i = j;
        }
    }

    @Override
    public String toString() {
        return "Heap{" +
                "heap=" + Arrays.toString(heap) +
                ", N=" + N +
                '}';
    }

    public static void main(String[] args) {
        Heap<Integer> hp = new Heap<>(20);
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        hp.insert((int)(Math.random()*100));
        System.out.println(hp);
        System.out.println(hp.delMax());
        System.out.println(hp);
    }
}
