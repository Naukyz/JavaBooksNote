package ThinkInJava.c15.c8;
//: generics/InstantiateGenericType.java

import static ThinkInJava.util.Utils.*;

class ClassAsFactory<T> {
    private T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
}

public class InstantiateGenericType {
    public static void main(String[] args) {
        try {
            ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
            print("ClassAsFactory<Employee> succeeded");
        } catch (Exception e) {
            print("ClassAsFactory<Employee> failed");
        }
        try {
            ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
            print("ClassAsFactory<Integer> succeeded");
        } catch (Exception e) {
            print("ClassAsFactory<Integer> failed");
        }
    }
} /* Output:
ClassAsFactory<Employee> succeeded
ClassAsFactory<Integer> failed
*///:~
