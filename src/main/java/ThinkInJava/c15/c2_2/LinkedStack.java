package ThinkInJava.c15.c2_2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/21
 */
public class LinkedStack<T> {

    private static class Node<T> {
        private T item;
        private Node<T> next;

        private Node() {
        }

        private Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<T> top = new Node<>();

    public void push(T item) {
        top = new Node<>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!end()) {
            top = top.next;
        }
        return result;
    }

    public boolean end() {
        return top.next == null;
    }


}

