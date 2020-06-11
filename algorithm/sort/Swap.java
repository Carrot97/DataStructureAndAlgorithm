package sort;

public class Swap {
    public static void swap(MyInteger a, MyInteger b) {
        int tmp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(tmp);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void backward(int[] arr, int start, int end, int offset, int interval) {
        for (int i=end; i >= start; i -= interval) {
            arr[i+offset] = arr[i];
        }
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 5;
        MyInteger ai = new MyInteger(a);
        MyInteger bi = new MyInteger(b);
        Swap.swap(ai, bi);
        a = ai.getValue();
        b = bi.getValue();
        System.out.println(a + " " + b);
    }
}
