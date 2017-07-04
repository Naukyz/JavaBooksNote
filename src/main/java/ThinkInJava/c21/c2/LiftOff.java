package ThinkInJava.c21.c2;

public class LiftOff implements Runnable {
    public static void main(String[] args) {
        LiftOff a = new LiftOff();
        a.run();

    }

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "(" + id + ")" + " " + (countDown > 0 ? countDown : "LiftOff");
    }

    @Override
    public void run() {

        while (countDown-- > 0) {
            if (countDown == 0) {
                System.out.println(status() + "  ");
            } else {
                System.out.print(status() + "  ");
            }
            Thread.yield();
        }
    }
}
