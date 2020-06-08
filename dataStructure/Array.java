import java.util.Arrays;
import java.util.Iterator;

public class Array<T> implements Iterable<T>{
    private T[] baseStore = null;
    private int size = 0;

    Array(T[] initArray){
        baseStore = initArray;
        size = initArray.length;
    }

    public void expand(){
        // 递增策略
        // 复杂度O(n^2) 装填因子≈100%
//        T[] newStore = (T[]) new Object[size+5];
        // 加倍策略（优）
        // 复杂度O(n) 装填因子>50%
        T[] newStore = (T[]) new Object[size << 1];
        for (int i=0; i<baseStore.length; i++)
            newStore[i] = baseStore[i];
//        System.arraycopy(baseStore, 0, newStore, 0, baseStore.length);
        baseStore = newStore;
    }

    public void add(T newData){
        if (size >= baseStore.length)
            expand();
        baseStore[size] = newData;
        size += 1;
    }

    public void add(int idx, T newData){
        if (idx < 0 || idx > size)
            System.out.println("illegal index");
        else {
            if (size >= baseStore.length)
                expand();
            for (int i=size-1; i>=idx; i--)
                baseStore[i+1] = baseStore[i];
//            System.arraycopy(baseStore, idx, baseStore, idx + 1, size - idx);
            baseStore[idx] = newData;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new arrayIterator();
    }

    private class arrayIterator implements Iterator<T> {
        private int i = 0;

        @Override
        public boolean hasNext() { return baseStore[i] != null; }

        @Override
        public T next() { return baseStore[i++]; }

        @Override
        public void remove() {}
    }

    @Override
    public String toString() {
        return "Array{"
                 + Arrays.toString(baseStore) +
                '}';
    }

    public void print() {
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<Integer>(new Integer[]{1, 2, 3});
        System.out.println(arr);
        arr.add(4);
        System.out.println(arr);
        arr.add(1, 5);
        System.out.println(arr);
        arr.print(); // 使用迭代器输出数组
    }

}