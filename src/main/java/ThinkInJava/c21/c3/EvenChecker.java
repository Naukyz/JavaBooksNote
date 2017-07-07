package ThinkInJava.c21.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/5
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            // 不是偶数 便取消
            if (val % 2 != 0) {
                System.out.println(val + "not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executor.execute(new EvenChecker(generator, i));
        }
        executor.shutdown();
    }

    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
