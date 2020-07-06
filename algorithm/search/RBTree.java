package search;

import java.io.PipedOutputStream;
import java.util.LinkedList;

public class RBTree<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;
        private boolean color;

        public Node(Key key, Value value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root = null;

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // 将指定节点的子节点左旋
    private Node rotateLeft(Node x) {
        Node h = x.right;
        x.right = h.left;
        h.left = x;
        h.color = x.color;
        x.color = RED;
        h.N = x.N;
        x.N = 1 + size(x.left) + size(x.right);
        return h;
    }

    // 将指定节点的子节点左旋
    private Node rotateRight(Node x) {
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.color = x.color;
        x.color = RED;
        h.N = x.N;
        x.N = 1 + size(x.left) + size(x.right);
        return h;
    }


    // 上交一个节点
    private void flipColors(Node x) {
        x.left.color = BLACK;
        x.right.color = BLACK;
        x.color = RED;
    }


    // 下借一个节点
    private void flipColorsReverse(Node x) {
        x.left.color = RED;
        x.right.color = RED;
        x.color = BLACK;
    }


    // 将指定键值对插入树中
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)  x.left = put(x.left, key, value);
        else if (cmp > 0) x.right =  put(x.right, key, value);
        else x.value = value;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 左子节点向其他节点借节点（变红）
    private Node moveRedLeft(Node x) {
        flipColorsReverse(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColorsReverse(x);
        }
        return x;
    }


    // 右子节点向其他节点借节点（变红）
    private Node moveRedRight(Node x) {
        flipColorsReverse(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColorsReverse(x);
        }
        return x;
    }


    // 平衡操作
    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    // 删除键值最小的节点
    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        root.color = BLACK;
    }

    public Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }


    // 删除键值最da的节点
    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        root.color = BLACK;
    }

    public Node deleteMax(Node x) {
        // 由于最后一个右链接为黑链接，删除后将破坏平衡
        if (isRed(x.left)) x = rotateRight(x);
        if (x.right == null) return null;
        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }


    // 删除指定节点
    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        root.color = BLACK;
    }

    public Node delete(Node x, Key key) {
        if(key.compareTo(x.key) < 0){
            if(x.left == null) return null;
            if(isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(x.left,key);
        } else {                  // 当目标键大于当前键的时候，我们向右移动，并做与deleteMax相同的操作，如果相同的话，我们使用和二叉树的删除一样的操作，获取当前键的右子树的最小健，然后交换，并将目标键删除
            if(isRed(x.left))
                x = rotateRight(x);
            if(key.compareTo(x.key) == 0 && x.right == null)
                return null;
            if(!isRed(x.right) && isRed(x.right.left))
                x = moveRedRight(x);
            if(key.compareTo(x.key) == 0){
                x.value = get(x.right, min(x.right).key);
                x.key = min(x.right).key;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, key);
        }
        return balance(x);
    }


    // 获取小于该节点的所有节点数量
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
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
        RBTree<Character, Integer> rbt = new RBTree<>();
        for (int i = 0; i < N; i++) {
            key = (char) Math.round(Math.random() * 25 + 65);
            value = (int) (Math.random() * 100);
            rbt.put(key, value);
        }
        rbt.print();
        System.out.println("----------");
        rbt.delete('F');
        rbt.print();
//        System.out.println("----------");
//        rbt.deleteMax();
//        rbt.deleteMin();
//        rbt.print();

    }
}
