package ThinkInJava.c15.c11;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/30
 */
public class A {
    public static void main(String[] args) {

        Person<Person> p1 = new Person<>("p1");
        Person<Person> p2 = new Person<>("p2");
        Person<Person> p3 = new Person<>("p3");
        Person<Person> p4 = new Person<>("p4");


        List<Person> list1 = new ArrayList<>(Arrays.asList(p1, p2));
        p3.setList(list1);
        List<Person> list2 = new ArrayList<>(Arrays.asList(p1, p2, p3));
        p4.setList(list2);
        List<Person> list3 = new ArrayList<>(Arrays.asList(p3, p4));

        String s = b(list3);

        List<Person<Person>> list4 = a(s, new TypeToken<List<Person<Person>>>() {
        });
        System.out.println(list4);

        Person<Person> pp = list4.get(1);
        List<Person> list5 = pp.getList();

        System.out.println(list5);


    }

    public static <T> T a(String s, TypeToken<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(s, t.getType());
    }

    public static String b(Object s) {
        Gson gson = new Gson();
        return gson.toJson(s);
    }

}

class Person<T> {
    private String name;

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Person(String name) {
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
        return "Person{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}