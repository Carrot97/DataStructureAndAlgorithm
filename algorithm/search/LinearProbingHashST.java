package search;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int m) {
        M = m;
        N = 0;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public LinearProbingHashST() {
        N = 0;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void resize(int cap) {
        LinearProbingHashST<Key, Value> tmp = new LinearProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null)
                tmp.put(keys[i], values[i]);
        }
        keys = tmp.keys;
        values = tmp.values;
        M = tmp.M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(M * 2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    public void delete(Key key) {
        if (get(key) == null) return;
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;
        keys[i] = null;
        values[i] = null;
        N--;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToReDo = keys[i];
            Value valueToReDo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToReDo, valueToReDo);
            i = (i + 1) % M;
        }
        if (N > 0 && N <= M/8) resize(M / 2);
    }

    public static void main(String[] args) {
        final int N = 15;
        char key;
        int value;
        LinearProbingHashST<Character, Integer> lph = new LinearProbingHashST<>();  // 必须为质数
        for (int i = 0; i < N; i++) {
            key = (char) Math.round(Math.random() * 25 + 65);
            value = (int) (Math.random() * 100);
            lph.put(key, value);
        }
        System.out.println(lph.get('F'));
    }
}
