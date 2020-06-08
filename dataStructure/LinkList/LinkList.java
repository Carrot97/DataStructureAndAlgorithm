package LinkList;

import java.util.Iterator;

public class LinkList<E> implements Iterable<E> {
    private int len = 0;
    protected Node<E> head = null;

    // 构建迭代器遍历所有元素
    @Override
    public Iterator<E> iterator() {
        return new listIterator();
    }

    private class listIterator implements Iterator<E> {
        private Node<E> current = head;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {}
    }

    //    内部类，单个节点
    public static class Node<E> {
        private E data;
        protected Node<E> next = null;

        public Node(E data){
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

//    查询链表长度
    public int length(){
        return this.len;
    }

//    判断链表是否为空
    public boolean isEmpty(){
        boolean isEmpty = false;
        if (this.head == null)
            isEmpty = true;
        return isEmpty;
    }

//    两种增加元素的方法append和insert
//    append：在结尾增加元素
//    insert：在指定位置增加元素（若索引不存在则报错）
    public void append(E newData){
        Node<E> newNode = new Node<E>(newData);
        if (isEmpty())
            this.head = newNode;
        else {
            Node<E> p = this.head;
            while (p.next != null)
                p = p.next;
            p.next = newNode;
        }
        this.len++;
    }

    public void insert(int idx, E newData){
        Node<E> newNode = new Node<E>(newData);
        if (isEmpty())
            this.head = newNode;
        else {
            if (idx == 0){
                newNode.next = head;
                head = newNode;
            }
            else {
                Node<E> p = this.head;
                for (int i = 0; i < idx - 1; i++) {
                    p = p.next;
                    if (p == null) {
                        System.out.println("out of index");
                        return;
                    }
                }

                if (p.next == null)
                    p.next = newNode;
                else {
                    newNode.next = p.next;
                    p.next = newNode;
                }
            }
        }
        this.len++;
    }

//    两种检索方法loc和index
//    loc：查找指定索引（返回数据的值，若索引错误报错）
//    index：查找第一个指定值（返回数据的索引，若无改值则报告）
    public E loc(int idx){
        Node<E> p = head;
        for (int i=0; i<idx; i++){
            if (p == null) {
                return null;
            }
            p = p.next;
        }
        if (p == null) return null;
        else return p.data;
    }

    public int index(E data) {
        Node<E> p = head;
        int i = 0;
        while (p != null){
            if (p.data == data)
                return i;
            p = p.next;
            i++;
        }
        System.out.println("not found");
        return -1;
    }

//    两种删除方法delete和remove
//    delete：删除指定索引（索引输出错误报错）
//    remove：删除第一个指定元素（无指定元素报告）
    public void delete(int idx){
        Node<E> p = this.head;
        if (idx == 0){
            this.head = p.next;
            // 释放内存
            p = null;
        }
        else {
            for (int i = 0; i < idx - 1; i++) {
                p = p.next;
                if (p == null) {
                    System.out.println("out of index");
                    return;
                }
            }
            if (p.next == null) System.out.println("out of index");
            else {
                Node<E> q = p;
                p = p.next;
                q.next = p.next;
                p = null;
            }
        }
        this.len--;
    }

    public void remove(E data){
        int idx = index(data);
        delete(idx);
    }

//    链表整体输出方法
//    上：使用循环输出
//    下：使用迭代器输出
    public void print(){
        if (isEmpty())
            System.out.println("no element");
        else {
            System.out.print("length:" + this.len);
            System.out.print(" data:[");
            Node<E> p = head;
            do {
                System.out.print(p.toString() + ",");
                p = p.next;
            } while (p != null);
            System.out.println("\b]");
        }
    }

    public void printByIter() {
        Iterator iter = this.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
        }
    }

    public static void main(String[] args) {
        LinkList<Integer> ll = new LinkList<Integer>();
        ll.append(1);
        ll.append(2);
        ll.insert(0, 3);
        ll.print();
        System.out.println(ll.loc(1));
        System.out.println(ll.index(1));
        ll.delete(0);
        ll.print();
        ll.remove(2);
        ll.print();
        ll.printByIter();
    }
}