package ThinkInJava.c21.c4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/8
 */
class Count {
    private int count = 0;
    private Random random = new Random();

    public synchronized int increment() {
        int temp = count;
        if (random.nextBoolean()) {
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled;

    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public String toString() {
        return "Entrance{" +
                "number=" + number +
                ", id=" + id +
                '}';
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total:" + count.increment());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }
}

public class OrnamentalGarden {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new Entrance(i));
        }
        executor.shutdown();
        try {
            Thread.sleep(3000);
            Entrance.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // 等待每个任务结束 如果所有的任务在超时时间到达之前全部结束 则返回true 否则返回false
            // 表示不是所有的任务都已经结束 会导致每个任务都退出其run()方法
            if (!executor.awaitTermination(250, TimeUnit.MILLISECONDS)) {
                System.out.println("Some tasks were not terminated!");
            }
            System.out.println("Total:" + Entrance.getTotalCount());
            System.out.println("Sum of Entrances:" + Entrance.sumEntrances());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

