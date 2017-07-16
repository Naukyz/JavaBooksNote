package ThinkInJava.c21.c4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/16
 */
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(sender);
        executor.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        executor.shutdownNow();
    }
}

class Sender implements Runnable {
    private Random rand = new Random();
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    // write 不会阻塞
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Sender " + e.toString());
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            char c;
            while (true) {
                // 如果没有会阻塞 但是可以中断 如果改成 System.in.read() 不能打断
                c = (char) in.read();
                System.out.println("Read:" + c + ",");
                System.out.println("char -> int " + (int) c);

            }
        } catch (IOException e) {
            System.out.println("Receiver " + e.toString());
        }
    }
}
