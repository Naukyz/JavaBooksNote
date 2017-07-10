package ThinkInJava.c21.c4;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/10
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        test1(); // 会按照正常逻辑结束
//        test2();
    }

    public static void test1() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        executor.execute(new IOBlocked(socketInput));
        Thread.sleep(100);
        System.out.println("Shutting down all Threads");
        executor.shutdownNow();
        Thread.sleep(1);
        System.out.println("Closing " + socketInput.getClass().getName());
        socketInput.close();
    }

    public static void test2() throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new IOBlocked(System.in));
        Thread.sleep(100);
        System.out.println("Shutting down all Threads");
        executor.shutdownNow();
        Thread.sleep(1);
        System.out.println("Closing " + System.in.getClass().getName());
        System.in.close();
    }
}
