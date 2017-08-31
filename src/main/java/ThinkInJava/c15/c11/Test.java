package ThinkInJava.c15.c11;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/31
 */
public class Test<T> implements Serializable {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        Test<String> a = new Test<>();
        a.setT("Test");
        Test<Test<String>> aa = new Test<>();
        aa.setT(a);
        try {
            out = new ObjectOutputStream(new FileOutputStream("D:\\a.txt"));
            in = new ObjectInputStream(new FileInputStream("D:\\a.txt"));
            out.writeObject(aa);
            Test<Test<String>> a2 = (Test<Test<String>>) in.readObject();
            System.out.println(a2.getT().getT());
            System.out.println(a2.getT().getT().getClass());
            System.out.println(a2.getT());
            System.out.println(a2.getT().getClass());
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            IOUtils.closeQuietly(out, in);
        }
    }
}
