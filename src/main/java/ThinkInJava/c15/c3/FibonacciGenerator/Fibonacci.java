package ThinkInJava.c15.c3.FibonacciGenerator;

import ThinkInJava.util.Generator;

import java.util.Iterator;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Fibonacci implements Generator<Integer>, Iterable<Integer> {
    public Fibonacci() {
    }

    private int size;
    private int count;

    public Fibonacci(int size) {
        this.size = size;
    }

    private int fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator();
    }

    private class FibonacciIterator implements Iterator<Integer> {

        private int count = size;

        @Override
        public boolean hasNext() {
            count--;
            return count >= 0;
        }

        @Override
        public Integer next() {
            return Fibonacci.this.next();
        }
    }
}
