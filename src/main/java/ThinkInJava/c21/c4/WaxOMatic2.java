package ThinkInJava.c21.c4;

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
        Car3 car3 = new Car3();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new WaxOff3(car3));
        executor.execute(new WaxOn3(car3));
        Thread.sleep(5000);
        executor.shutdownNow();

    }

}

class Car3 {
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

class WaxOn3 implements Runnable {
    private Car3 car3;

    public WaxOn3(Car3 car3) {
        this.car3 = car3;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
//                System.out.println("Wax On!");
//                Thread.sleep(200);
//                car3.waxed();
//                car3.waitForBuffing();

                car3.waxed();
                Thread.sleep(200);
                System.out.println("Wax On!");
                car3.waitForBuffing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax On task");
    }
}

class WaxOff3 implements Runnable {
    private Car3 car3;

    public WaxOff3(Car3 car3) {
        this.car3 = car3;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
//                car3.waitForWaxing();
//                System.out.println("Wax Off !");
//                Thread.sleep(200);
//                car3.buffed();

                car3.buffed();
                Thread.sleep(200);
                System.out.println("Wax Off !");
                car3.waitForWaxing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax Off task");

    }
}
