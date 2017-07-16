package ThinkInJava.c21.c5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/15
 */
public class Restaurant {

    Meal meal;
    ExecutorService executor = Executors.newCachedThreadPool();
    final WaitPerson waitPerson = new WaitPerson(this);
    final Chef chef = new Chef(this);

    public Restaurant() {
        executor.execute(chef);
        executor.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }

}

class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "orderNum=" + orderNum +
                '}';
    }
}


class WaitPerson implements Runnable {

    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();

                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}


class Chef implements Runnable {

    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        wait();

                    }
                }
                if (++count == 10) {
                    System.out.println("Out of food , closing");
                    restaurant.executor.shutdownNow();
                }
                // shutdownNow 将向所有有ExecutorService启动的任务发送interrupt()
                // 但是在chef中 任务并没有获得该interrupt()之后立即关闭 因为当任务试图进入一个
                // 可中断的阻塞操作时 这个中断只能抛出InterruptedException
                // 如果移除sleep()的调用 那么这个任务将回到run()循环的顶部并由于Thread.interrupted()测试而退出
                System.out.println("Order up !");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();

                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}












