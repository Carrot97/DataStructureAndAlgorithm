import java.util.Arrays;

public class Array {
    private int[] baseStore = null;
    private int size = 0;

    Array(int[] initArray){
        baseStore = initArray;
        size = initArray.length;
    }

    public void expand(){
        int[] newStore = new int[size+5];
        for (int i=0; i<baseStore.length; i++)
            newStore[i] = baseStore[i];
//        System.arraycopy(baseStore, 0, newStore, 0, baseStore.length);
        baseStore = newStore;
    }

    public void add(int newData){
        if (size >= baseStore.length)
            expand();
        baseStore[size] = newData;
        size += 1;
    }

    public void add(int idx, int newData){
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
    public String toString() {
        return "Array{"
                 + Arrays.toString(baseStore) +
                '}';
    }

    public static void main(String[] args) {
        Array arr = new Array(new int[]{1, 2, 3});
        System.out.println(arr);
        arr.add(4);
        System.out.println(arr);
        arr.add(1, 5);
        System.out.println(arr);
    }
}