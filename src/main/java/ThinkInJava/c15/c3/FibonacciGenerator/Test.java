package ThinkInJava.c15.c3.FibonacciGenerator;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Test {
    public static void main(String[] args) {
        Long a = System.nanoTime();

        for (Integer integer : FobonacciUtil.f1(40)) {
            System.out.print(integer + " ");
        }

        System.out.println("\n---" + String.valueOf(System.nanoTime() - a));
        a = System.nanoTime();

        for (Integer integer : FobonacciUtil.f2(40)) {
            System.out.print(integer + " ");
        }
        System.out.println("\n---" + String.valueOf(System.nanoTime() - a));

    }
}

class FobonacciUtil {
    public static Iterable<Integer> f1(int size) {
        return new Fibonacci(size);
    }

    public static Iterable<Integer> f2(int size) {
        return new Fibonacci2(size);
    }
}