package search;

import java.util.Arrays;
import java.util.Comparator;

public class S3T<Key extends Comparable<Key>, Value> {
    private static class Key_Value<Key extends Comparable<Key>, Value> {
        private Key key;
        private Value value;

        public Key_Value() {
        }

        public Key_Value(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "-->" + value;
        }

        // 比较器（按照key的大小）
        public static class KeyOrder implements Comparator<Key_Value> {

            @Override
            public int compare(Key_Value o1, Key_Value o2) {
                return o1.key.compareTo(o2.key);
            }
        }
    }

    private Key_Value[] key_values;
    private int N = 0;

    public S3T() {
        key_values = new Key_Value[5];
        for (int i = 0; i < key_values.length; i++)
            key_values[i] = new Key_Value();
    }

    public S3T(Key[] keys, Value[] values) {
        key_values = new Key_Value[keys.length];
        for (int i = 0; i < keys.length; i++) {
            key_values[i] = new Key_Value();
            put(keys[i], values[i]);
        }
    }

    public Key_Value[] getST() {
        return key_values;
    }

    public int length() {
        return N;
    }

    // 对象数组的输出方法
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Key_Value ky : key_values) {
            if (ky == null || ky.key == null) break;
            result.append(ky.toString()).append("\n");
        }
        return result.toString();
    }

    // 对数组进行扩容（加倍策略）
    public void expand(){
        Key_Value[] key_values = new Key_Value[this.key_values.length << 1];
        if (N >= 0) System.arraycopy(this.key_values, 0, key_values, 0, N);
        this.key_values = key_values;
    }


    // 将新的键值对加入对象数组
    // 排序版
//    public void put(Key key, Value value) {
//        for (int i = 0; i < N; i++) {
//            // 逐个元素进行比较，若key相同则更新其value
//            if (key_values[i].key.equals(key)) {
//                key_values[i].setValue(value);
//                return;
//            }
//            // 若发现有key大的元素，则将新元素插入该位置
//            else if (key_values[i].key.compareTo(key) > 0) {
//                if (N+1 >= key_values.length) expand();
//                for (int j = N-1; j >= i; j--) {
//                    key_values[j+1] = key_values[j];
//                }
//                key_values[i] = new Key_Value<>(key, value);
//                N++;
//                return;
//            }
//        }
//        // 若发现所有元素key都小，则将新元素插入至末尾
//        if (N+1 >= key_values.length) expand();
//        key_values[N++] = new Key_Value<>(key, value);
//    }
    // 非排序版
    public void put(Key key, Value value) {
        for (int i = 0; i < N; i++) {
            if (key_values[i].key.equals(key)) {
                key_values[i].value = value;
                return;
            }
        }
        if (N+1 > key_values.length) expand();
        key_values[N++] = new Key_Value<>(key, value);
    }


    // 获取指定key的键值对（若不存在指定key则返回null）
    // 排序版
//    public Value get(Key key) {
//        for (int i = 0; i < N; i++) {
//            if (key_values[i].key.compareTo(key) > 0)
//                return null;
//            if (key_values[i].key.equals(key))
//                return (Value) key_values[i].value;
//        }
//        return null;
//    }
    // 未排序版
    public Value get(Key key) {
        for (int i = 0; i < N; i++) {
            if (key_values[i].key.equals(key))
                return (Value) key_values[i].value;
        }
        return null;
    }


    // 对整个对象数组按照key的大小进行排序
    public void sort() {
        Key_Value.KeyOrder c = new Key_Value.KeyOrder();
        Arrays.sort(this.key_values, c);
    }

    public static void main(String[] args) {
        final int N = 10;
        Character[] keys = new Character[N];
        Integer[] values = new Integer[N];
        for (int i = 0; i < 10; i++) {
            keys[i] = (char) Math.round(Math.random()*25 +65);
            values[i] = (int) (Math.random()*100);
        }
        System.out.println(Arrays.toString(keys));
        System.out.println(Arrays.toString(values));
        S3T<Character, Integer> arr = new S3T<>(keys, values);
        System.out.println(arr.toString());
//        arr.sort();
//        System.out.println(arr.toString());
    }
}
