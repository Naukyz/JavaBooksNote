package ThinkInJava.c15.c8;

import java.util.Arrays;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/8/27
 */
public interface MyList<T> {
    void put(int index, T t);

    T get(int index);

    T[] toArray(Class<T[]> clazz);
}

class B<T> implements MyList<T> {

    private Object[] aa;

    public B(int size) {
        this.aa = new Object[size];
    }

    @Override
    public void put(int index, T t) {
        aa[index] = t;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        return (T) aa[index];
    }

    @Override
    public T[] toArray(Class<T[]> clazz) {
        return Arrays.copyOf(aa, aa.length, clazz);
    }

    public static void main(String[] args) {
        MyList<String> b = new B<>(2);
        b.put(1, "A");
        String aa = b.get(1);
        System.out.println(aa);
        System.out.println(Arrays.toString(b.toArray(String[].class)));

    }

}