package ThinkInJava.c21.c4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/14
 */
public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new WaxOff(car));
        executor.execute(new WaxOn(car));
        Thread.sleep(5000);
        executor.shutdownNow();
    }
}

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waxed();
                Thread.sleep(200);
                System.out.println("Wax On ");
                car.waitForBuffing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax On Task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.buffed();
                Thread.sleep(200);
                System.out.println("Wax Off");
                car.waitForWaxing();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Ending Wax Off Task");
    }
}
