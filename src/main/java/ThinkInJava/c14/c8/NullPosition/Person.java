package ThinkInJava.c14.c8.NullPosition;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Person {
    private final String first;
    private final String last;
    private final String address;

    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person : " + first + " " + last + " " + address;
    }

    private static class NullPerson extends Person implements Null {

        private NullPerson() {
            super("None", "None", "None");
        }

    }

    public static final Person NULL = new NullPerson();
}

