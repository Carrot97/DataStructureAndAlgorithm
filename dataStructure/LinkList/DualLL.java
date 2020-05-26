package LinkList;

public class DualLL<E> extends LinkList<E> {
    private DualNode<E> tail = null;

    public static class DualNode<E> extends Node<E>{
        private DualNode<E> pre = null;

        public DualNode(E data) {
            super(data);
        }
    }

    public void append(E newData){
        DualNode<E> newNode = new DualNode<E>(newData);
        if (head == null)
            head = newNode;
        else {
            newNode.pre = tail;
            tail.next = newNode;
        }
        tail = newNode;
    }

    public void add(int idx, E newData){
        DualNode<E> newNode = new DualNode<E>(newData);
        if (head == null)
            head = newNode;
        else {
            DualNode<E> p = (DualNode<E>) head;
            for (int i=0; i<idx-1; i++){
                p = (DualNode<E>) p.next;
                if (p == null) {
                    System.out.println("out of index");
                    return;
                }
            }

            if (p.next == null) {
                p.next = newNode;
                newNode.pre = p;
                tail = newNode;
            }
            else {
                DualNode<E> p_ = null;
                p_ = (DualNode<E>) p.next;
                p_.pre = newNode;
                newNode.next = p.next;
                newNode.pre = p;
                p.next = newNode;
            }
        }
    }

    public void print(boolean reverse){
        if (reverse) {
            if (head == null)
                System.out.println("no element");
            else {
                System.out.print("[");
                DualNode<E> p = tail;
                do {
                    System.out.print(p.toString() + ",");
                    p = p.pre;
                } while (p != null);
                System.out.println("\b]");
            }
        }
        else super.print();
    }

    public static void main(String[] args) {
        DualLL<Integer> dll = new DualLL<Integer>();
        dll.append(1);
        dll.append(2);
        dll.add(1, 3);
        dll.print();
        dll.print(true);
        System.out.println(dll.loc(1));
    }
}