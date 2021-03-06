package ThinkInJava.c10.c8;

import ThinkInJava.util.Utils;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/6
 */
interface Incrementable {
    void increment();
}

// Very simple to just implement the interface:
class Callee1 implements Incrementable {
    private int i = 0;

    public void increment() {
        i++;
        Utils.print(i);
    }
}

class MyIncrement {
    public void increment() {
        Utils.print("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}

// If your class must implement increment() in
// some other way, you must use an inner class:
class Callee2 extends MyIncrement {
    private int i = 0;

    public void increment() {
        super.increment();
        i++;
        Utils.print(i);
    }

    private class Closure implements Incrementable {
        public void increment() {
            // Specify outer-class method, otherwise
            // you'd get an infinite recursion:
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Caller caller1 = new Caller(c1);
        caller1.go();
        caller1.go();
        System.out.println("---");

        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller2.go();
        caller2.go();
    }
}