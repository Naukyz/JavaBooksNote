package ThinkInJava.c21.c3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/6
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void unTimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock():" + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured;
        try {
            // 经试验 感觉会阻塞
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        try {
            System.out.println("tryLock(2,TimeUnit.SECONDS):" + captured);
        } finally {
            if (captured) {
                lock.unlock();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final AttemptLocking al = new AttemptLocking();
        al.unTimed();
        al.timed();
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        Thread.sleep(1000);
        al.unTimed();
        al.timed();
    }

}
