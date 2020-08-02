package search;


public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value value;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    public Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key, val, d+1);
        else x.value = val;
        return x;
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        tst.put("apple", 1);
        tst.put("banana", 2);
        tst.put("pear", 3);
        tst.put("peach", 4);
        tst.put("orange", 5);
        System.out.println(tst.get("pear"));
    }
}
