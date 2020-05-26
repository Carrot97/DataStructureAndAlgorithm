// 用链表实现栈
import LinkList.LinkList;

public class Stack<E> {
    private LinkList<E> baseStore = new LinkList<E>();

    public void pop(E data){
        this.baseStore.insert(0, data);
    }

    public E push(){
        E data = this.baseStore.loc(0);
        this.baseStore.delete(0);
        return data;
    }

    public void print(){
        baseStore.print();
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.pop(1);
        s.pop(2);
        s.pop(3);
        s.print();
        System.out.println(s.push());
        System.out.println(s.push());
        s.print();
    }
}
