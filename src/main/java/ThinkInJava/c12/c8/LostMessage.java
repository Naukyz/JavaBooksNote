package ThinkInJava.c12.c8;

// 里面的try抛出异常A 没有catch 想要在外面try中catch
// 但是里面的finally抛出第二个异常B 结果 外面的try中只catch了第二个异常B
// 所以第二个异常抛出前 需要 处理第一个异常
class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

public class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main(String[] args) {
        try {
            System.out.println(1);
            LostMessage lm = new LostMessage();
            try {
                System.out.println(2);
                lm.f();
            } finally {
                System.out.println(3);
                lm.dispose();
            }
        } catch (Exception e) {
            System.out.println(4);
            System.out.println(e);
        }
    }
} /* Output:
A trivial exception
*///:~
