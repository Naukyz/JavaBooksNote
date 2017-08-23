//: typeinfo/toys/GenericToyTest.java
// Testing class Class.
package ThinkInJava.c14.c3;

public class GenericToyTest {
    public static void main(String[] args) throws Exception {
        Class<FancyToy> ftClass = FancyToy.class;
        // Produces exact type:
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
        //<? super FancyToy> 使泛型信息丢失一部分
        // This won't compile:
        // Class<Toy> up2 = ftClass.getSuperclass();
        // Only produces Object:
        Object obj = up.newInstance();

        Toy obj2 = (Toy) up.newInstance();


    }
} ///:~
