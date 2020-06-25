package sort;

import java.util.Arrays;
import java.util.Comparator;

public class MultiKey {
    public static class Transaction {
        private final int date;
        private final double amount;

        public Transaction() {
            this.date = (int)(Math.random()*30);
            this.amount = Math.random()*100;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "date=" + date +
                    ", amount=" + amount +
                    '}';
        }

        public static class WhenOrder implements Comparator<Transaction> {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return Integer.compare(o1.date, o2.date);
            }
        }

        public static class AmountOrder implements Comparator<Transaction> {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return Double.compare(o1.amount, o2.amount);
            }
        }
    }

    public static void sort(Transaction[] ta, Comparator c) {
        int N = ta.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(c, ta[j], ta[j-1]); j--)
                swap(ta, j, j-1);
    }

    private static void swap(Transaction[] ta, int i, int j) {
        Transaction tmp = ta[i];
        ta[i] = ta[j];
        ta[j] = tmp;
    }

    private static boolean less(Comparator c, Transaction o1, Transaction o2) {
        return c.compare(o1, o2) < 0;
    }

    public static void main(String[] args) {
        // 初始化对象数组
        Transaction[] ta = new Transaction[10];
        for (int i = 0; i < ta.length; i++) ta[i] = new Transaction();
        System.out.println(Arrays.toString(ta));

        // 初始化比较器
        Transaction.AmountOrder c = new Transaction.AmountOrder();

//        sort(ta, c);
        Arrays.sort(ta, c);
        System.out.println(Arrays.toString(ta));
    }
}
