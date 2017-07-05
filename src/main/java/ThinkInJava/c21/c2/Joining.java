package ThinkInJava.c21.c2;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/5
 */

public class Joining{
    public static void main(String[] args){
        Sleeper sleepy = new Sleeper("Sleepy",1500);
        Joiner dopey = new Joiner("Dopey",sleepy);

        Sleeper grumpy = new Sleeper("Grumpy",1500);
        Joiner doc = new Joiner("Doc",grumpy);
        grumpy.interrupt();

    }
}

class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int duration) {
        super(name);
        this.duration = duration;
        start();
    }

    @Override
    public void run() {

        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(
                    getName() + " was interrupted . isInterrupted():" + isInterrupted()

            );
            return;
        }
        System.out.println(getName() + "has awakened");

    }
}

class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try{
            sleeper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName()+" join completed");
    }
}


