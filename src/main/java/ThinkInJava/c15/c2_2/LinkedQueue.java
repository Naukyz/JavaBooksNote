package ThinkInJava.c15.c2_2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/21
 */
public class LinkedQueue<T> {

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> last;

        private Node() {
        }

        private Node(T item, Node<T> next, Node<T> last) {
            this.item = item;
            this.next = next;
            this.last = last;
        }
    }

    private Node<T> bottom = new Node<>();
    private Node<T> top = bottom;

    public void push(T item) {
        Node<T> node = new Node<>(item, null, top);
        top.next = node;
        top = node;
    }

    public T pull() {
        if (end()) {
            return bottom.item;
        }
        Node<T> node = bottom.next;
        T result = node.item;
        node.item = null;
        bottom = node;
        return result;
    }

    public boolean end() {
        return bottom == top;
    }
}

