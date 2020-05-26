import LinkList.LinkList;

public class Queue<E> {
    private LinkList<E> baseStore = new LinkList<E>();

    public void in(E data){
        this.baseStore.append(data);
    }

    public E out(){
        E data = this.baseStore.loc(0);
        this.baseStore.delete(0);
        return data;
    }

    public void print(){
        baseStore.print();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.in(1);
        q.in(2);
        q.in(3);
        q.print();
        System.out.println(q.out());
        System.out.println(q.out());
        q.print();
    }
}
