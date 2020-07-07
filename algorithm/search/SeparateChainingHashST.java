package search;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value> {
    private int N;
    private int M;
    private S3T<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        M = m;
        st = (S3T<Key, Value>[]) new S3T[M];
        for (int i = 0; i < M; i++)
            st[i] = new S3T<>();
    }

    // 普通哈希操作仅低位决定结果
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
        N++;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public static void main(String[] args) {
        final int N = 15;
        char key;
        int value;
        SeparateChainingHashST<Character, Integer> sch = new SeparateChainingHashST<>(7);  // 必须为质数
        for (int i = 0; i < N; i++) {
            key = (char) Math.round(Math.random() * 25 + 65);
            value = (int) (Math.random() * 100);
            sch.put(key, value);
        }
        System.out.println(sch.get('F'));
    }
}
