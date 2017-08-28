package ThinkInJava.c15.c8;//: generics/GenericArray2.java

public class GenericArray2<T> {
    private Object[] array;

    public GenericArray2(int sz) {
        array = new Object[sz];
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    // 因为 put 方法 有 T 所以 忽略警告没有问题
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep() {
        // Object 数组 并不能转换为 T数组 返回类型T[] 错误
        return (T[]) array; // Warning: unchecked cast
    }

    public static void main(String[] args) {
        GenericArray2<Integer> gai = new GenericArray2<Integer>(10);
        for (int i = 0; i < 10; i++)
            gai.put(i, i);
        for (int i = 0; i < 10; i++)
            System.out.print(gai.get(i) + " ");
        System.out.println();

        gai.put(0, 1);
        Integer a = gai.get(0);
        System.out.println(a);

        try {
            Integer[] ia = gai.rep();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/* Output: (Sample)
0 1 2 3 4 5 6 7 8 9
java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
*///:~
