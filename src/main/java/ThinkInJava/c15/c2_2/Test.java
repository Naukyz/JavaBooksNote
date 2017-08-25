package ThinkInJava.c15.c2_2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Test {
    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        System.out.println(linkedStack.pop());

        linkedStack.push("A");

        System.out.println(linkedStack.pop());

        linkedStack.push("B");
        linkedStack.push("C");

        while (!linkedStack.end()) {
            System.out.println(linkedStack.pop());
        }

        System.out.println(linkedStack.pop());

        System.out.println("---");

        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        System.out.println(linkedQueue.pull());

        linkedQueue.push("A");

        System.out.println(linkedQueue.pull());

        linkedQueue.push("B");
        linkedQueue.push("C");

        while (!linkedQueue.end()) {
            System.out.println(linkedQueue.pull());
        }

        System.out.println(linkedQueue.pull());

        System.out.println("---");

        LinkedStackQueue<String> linkedStackQueue = new LinkedStackQueue<>();

        System.out.println(linkedStackQueue.pop());
        System.out.println(linkedStackQueue.pull());

        linkedStackQueue.push("A");
        linkedStackQueue.push("B");
        linkedStackQueue.push("C");

        System.out.println(linkedStackQueue.pop());
        System.out.println(linkedStackQueue.pull());

        while (!linkedStackQueue.end()) {
            System.out.println(linkedStackQueue.pull());
        }

        System.out.println(linkedStackQueue.pop());

        linkedStackQueue.push("A");
        linkedStackQueue.push("B");
        linkedStackQueue.push("C");

        System.out.println(linkedStackQueue.pull());
        System.out.println(linkedStackQueue.pop());

        while (!linkedStackQueue.end()) {
            System.out.println(linkedStackQueue.pop());
        }

        System.out.println(linkedStackQueue.pull());

    }
}
