package sort;

public class Swap {
    public static void swap(MyInteger a, MyInteger b) {
        int tmp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(tmp);
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
