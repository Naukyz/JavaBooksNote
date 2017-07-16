package ThinkInJava.c21.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/15
 */
public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car2 car2 = new Car2();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new WaxOff2(car2));
        executor.execute(new WaxOn2(car2));
        Thread.sleep(5000);
        executor.shutdownNow();
    }
}

class Car2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean wanOn = false;

    public void waxed() {
        lock.lock();
        try {
            wanOn = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            wanOn = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!wanOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (wanOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn2 implements Runnable {
    private Car2 car2;

    public WaxOn2(Car2 car2) {
        this.car2 = car2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car2.waxed();
                Thread.sleep(200);
                System.out.println("Wax On!");
                car2.waitForBuffing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff2 implements Runnable {
    private Car2 car2;

    public WaxOff2(Car2 car2) {
        this.car2 = car2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car2.buffed();
                Thread.sleep(200);
                System.out.println("Wax Off !");
                car2.waitForWaxing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax Off task");
    }
}
