package ThinkInJava.c21.c4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/14
 */
public class WaxOnOff2 {
    public static void main(String[] args) throws InterruptedException {
        Car2 car = new Car2();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new A1(car));
        executor.execute(new A2(car));
        Thread.sleep(5000);
        executor.shutdownNow();
    }
}

class Car2 {
    private boolean flag = false;

    public synchronized void a1() {
        flag = true;
        System.out.println("a1");
        notifyAll();
    }

    public synchronized void a2() {
        flag = false;
        System.out.println("a2");
        notifyAll();
    }

    public synchronized void aa1() throws InterruptedException {
        // 使用 while 的原因
        // 被唤醒的时候需要再次检查
        while (flag) {
            wait();
        }
    }

    public synchronized void aa2() throws InterruptedException {
        while (!flag) {
            wait();
        }
    }
}

class A1 implements Runnable {
    private Car2 car;

    public A1(Car2 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(200);
                car.aa1();
                car.a1();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class A2 implements Runnable {
    private Car2 car;

    public A2(Car2 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(200);
                car.aa2();
                car.a2();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}