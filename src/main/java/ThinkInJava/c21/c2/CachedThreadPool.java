package ThinkInJava.c21.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new LiftOff());
        }
        // 防止新任务被提交给这个 Executor 当前线程将继续运行在shutdown() 被调用之前提交的所有任务
        executor.shutdown();
    }

}
