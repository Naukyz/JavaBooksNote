package ThinkInJava.c21.c4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/15
 */
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butteredQueue = new ToastQueue();
        ToastQueue finishedQueue = new ToastQueue();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Toaster(dryQueue));
        executor.execute(new Butterer(dryQueue, butteredQueue));
        executor.execute(new Jammer(butteredQueue, finishedQueue));
        executor.execute(new Eater(finishedQueue));
        Thread.sleep(5000);
        executor.shutdownNow();
    }
}

class Toast {
    public Toast(int id) {
        this.id = id;
    }

    public enum Status {
        DRY, BUTTERED, JAMMED
    }

    private Status status = Status.DRY;
    private final int id;

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random();

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(100 + rand.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        } catch (Exception e) {
            System.out.println("Toast interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (Exception e) {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.out.println("Error:" + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
    }
}
