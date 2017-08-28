package ThinkInJava.c15.c8;
//: generics/FactoryConstraint.java

interface FactoryI<T> {
    T create();
}

class Foo2<T> {
    public T x;

    public <F extends FactoryI<T>> Foo2(F factory) {
        x = factory.create();
    }
    // ...
}

class Foo3<T> {

    public T t;

    public Foo3(FactoryI<T> factory) {
        t = factory.create();
    }
    // ...
}

class IntegerFactory implements FactoryI<Integer> {
    public Integer create() {
        return 0;
    }
}

class Widget {
    public static class Factory implements FactoryI<Widget> {
        public Widget create() {
            return new Widget();
        }
    }
}

public class FactoryConstraint {
    public static void main(String[] args) {
        System.out.println(new Foo2<>(new IntegerFactory()).x);
        System.out.println(new Foo2<>(new Widget.Factory()).x);

        System.out.println(new Foo3<>(new IntegerFactory()).t);
        System.out.println(new Foo3<>(new Widget.Factory()).t);
    }
} ///:~
