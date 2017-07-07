package ThinkInJava.c21.c3;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/7
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static int nextSerialNumber() {
        return serialNumber++;
    }
}
