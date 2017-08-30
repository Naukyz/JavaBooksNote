package ThinkInJava.c15.c11;//: generics/ClassCasting.java


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;

public class ClassCasting {
    //@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        Widget w = new Widget("A");
        Widget B = new Widget("B");
        AA list = new AA();
        Collections.addAll(list, w, B);

        try {
            out = new ObjectOutputStream(new FileOutputStream("D:\\a.txt"));
            in = new ObjectInputStream(new FileInputStream("D:\\a.txt"));

            out.writeObject(list);
            out.writeObject(list);

            List<Widget> list1 = (AA) in.readObject();
            Widget widget = list1.get(0);
            System.out.println(widget);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            IOUtils.closeQuietly(out, in);
        }


    }
} ///:~

class Widget implements Serializable {
    private String name;

    public Widget(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "name='" + name + '\'' +
                '}';
    }
}

class AA extends ArrayList<Widget> implements Serializable {
}