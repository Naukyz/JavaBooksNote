package ThinkInJava.c15.c7;//: generics/GenericHolder.java

public class GenericHolder<T> {
    private T obj;

    public void set(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    /**
     * set 时 进行 编译时的检查 如果没有泛型 运行set时会进行类型检查
     * get 时 进行转型 (有泛型 或是 强转 都需要)
     */
    public static void main(String[] args) {
        GenericHolder<String> holder =
                new GenericHolder<String>();
        holder.set("Item");
        String s = holder.get();
    }
} ///:~
