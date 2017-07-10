package ThinkInJava.c21.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/10
 */
class NIOBlocked implements Runnable {
    private final SocketChannel sc;

    public NIOBlocked(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read() in " + this);
            sc.read(ByteBuffer.allocate(1));
        } catch (IOException e) {
//            throw new RuntimeException(e);
            System.out.println(e.toString());
        }
        System.out.println("Exiting NIOBlocked.run() " + this);
    }
}

public class NIOInterruption {
    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }

    public static void test1() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        Future<?> f = executor.submit(new NIOBlocked(sc1));
        executor.shutdown();
        Thread.sleep(1);
        f.cancel(true);
        Thread.sleep(1);
        sc1.close();
    }

    public static void test2() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
        SocketChannel sc2 = SocketChannel.open(isa);
        executor.execute(new NIOBlocked(sc2));
        executor.shutdown();
        executor.shutdownNow();
        Thread.sleep(1);
        sc2.close();
    }
}
