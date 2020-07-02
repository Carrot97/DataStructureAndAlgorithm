package search;

import java.util.LinkedList;

public class BinaryTree<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root = null;


    // 获取小于该节点的所有节点数量
    public int size() {
       return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


    // 将指定键值对插入树中
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)  x.left = put(x.left, key, value);
        else if (cmp > 0) x.right =  put(x.right, key, value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 获取指定key对应的value， 若树中无该key则返回null
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x.value;
        else if (cmp < 0) return get(x.left, key);
        else return get(x.right, key);
    }


    // 获取树中最小的key
    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }


    // 获取树中最大的key
    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }


    // 获取小于指定key的最大key
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    public Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t == null) return x;
        else return t;
    }


    // 获取大于指定key的最小key
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    public Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t == null) return x;
        else return t;
    }


    // 取得第k小的key
    public Key select(int k) {
        Node x = select(root, k);
        if (x == null) return null;
        else return x.key;
    }

    public Node select(Node x, int k) {
        if (x == null) return null;
        int n = size(x.left);
        if (n == k) return x;
        else if (k < n) return select(x.left, k);
        else return select(x.right, k-n-1);
    }

    // 取得树中比指定key小的key的数量
    public int rank(Key key) {
        return rank(root, key);
    }

    public int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        else if (cmp < 0) return rank(x.left, key);
        else return 1 + size(x.left) + rank(x.right, key);
    }


    // 删除最小key对应的节点
    public void deleteMin() {
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 删除最大key对应的节点
    public void deleteMax() {
        root = deleteMin(root);
    }

    public Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 删除指定key对应的节点
    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 中序遍历
    public void print() {
        print(root);
    }

    public void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key + "-->" + x.value);
        print(x.right);
    }


    // 范围查找（找出给定最大key和最小key之间的所有节点）
    public Iterable<Key> keys() {
        return keys();
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> ll = new LinkedList<>();
        keys(root, ll, lo, hi);
        return ll;
    }

    public void keys(Node x, LinkedList<Key> ll, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key), cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, ll, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) ll.add(x.key);
        if (cmphi > 0) keys(x.right, ll, lo, hi);
    }


    public static void main(String[] args) {
        final int N = 10;
        char key;
        int value;
        BinaryTree<Character, Integer> bt = new BinaryTree<>();
        for (int i = 0; i < N; i++) {
            key = (char) Math.round(Math.random()*25 +65);
            value = (int) (Math.random()*100);
            bt.put(key, value);
        }
        bt.print();
        System.out.println(bt.get('L'));
        System.out.println(bt.min());
        System.out.println(bt.max());
        System.out.println(bt.floor('F'));
        System.out.println(bt.ceiling('O'));
        System.out.println(bt.select(3));
        System.out.println(bt.rank('X'));
        bt.deleteMax();
        bt.deleteMin();
        bt.print();
        System.out.println(bt.keys('F', 'X'));

    }
}
