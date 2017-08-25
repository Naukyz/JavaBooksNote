package ThinkInJava.c15.c3.FibonacciGenerator;

import ThinkInJava.util.Generator;

import java.util.Iterator;

class Fibonacci2 implements Generator<Integer>, Iterable<Integer> {

    private int size;
    private int count;
    private int num1 = 1;
    private int num2 = 1;

    public Fibonacci2(int size) {
        this.size = size;
    }

    @Override
    public Integer next() {
        if (count == 0 || count == 1) {
            count++;
            return 1;
        } else {
            count++;
            int int3 = num1 + num2;
            num1 = num2;
            num2 = int3;
            return num2;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Fibonacci2Iterator();
    }

    private class Fibonacci2Iterator implements Iterator<Integer> {

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Integer next() {
            return Fibonacci2.this.next();
        }
    }

}