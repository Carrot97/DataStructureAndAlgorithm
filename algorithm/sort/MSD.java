package sort;

public class MSD {
    private static final int M = 15;
    private static final int R = 256;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
//        if (hi < lo + M) {
//            Insertion.sort(a);
//            return;
//        }

        int[] count = new int[R + 2];

        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 1]++;
        }

        for (int r = 0; r < R+1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = lo; i < hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i < hi; i++) {
            a[i] = aux[i-lo];
        }

        for (int r = 0; r < R; r++) {
            sort(a, lo+count[r], lo+count[r+1]-1, d+1);
        }
    }
}
