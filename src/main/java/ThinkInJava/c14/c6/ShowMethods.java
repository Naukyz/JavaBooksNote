package ThinkInJava.c14.c6;

import static ThinkInJava.util.Utils.*;

//: typeinfo/ShowMethods.java
// Using reflection to show all the methods of a class,
// even if the methods are defined in the base class.
// {Args: ShowMethods}
import java.lang.reflect.*;

import java.util.regex.*;


public class ShowMethods {
    private static String usage = "usage:\n" +
            "ShowMethods qualified.class.name\n" +
            "To show all methods in class or:\n" +
            "ShowMethods qualified.class.name word\n" +
            "To search for methods involving 'word'";
    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        args = new String[2];

        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }

        int lines = 0;

        try {
            args[0] = "ThinkInJava.c14.c6.ShowMethods";
            args[1] = "main";

            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();

            if (args.length <= 1) {
                for (Method method : methods) {
                    // 把一些不需要的信息删除
                    print(p.matcher(method.toString()).replaceAll(""));
                }

                for (Constructor ctor : ctors)
                    print(p.matcher(ctor.toString()).replaceAll(""));

                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods)
                    // 包含有要查找的字符串
                    if (method.toString().contains(args[1])) {
                        print(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }

                for (Constructor ctor : ctors)
                    // 包含有要查找的字符串
                    if (ctor.toString().indexOf(args[1]) != -1) {
                        print(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
        } catch (ClassNotFoundException e) {
            print("No such class: " + e);
        }
    }
} /* Output:
public static void main(String[])
public native int hashCode()
public final native Class getClass()
public final void wait(long,int) throws InterruptedException
public final void wait() throws InterruptedException
public final native void wait(long) throws InterruptedException
public boolean equals(Object)
public String toString()
public final native void notify()
public final native void notifyAll()
public ShowMethods()
*/
//:~
