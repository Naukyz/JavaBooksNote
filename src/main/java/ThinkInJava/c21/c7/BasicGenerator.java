package ThinkInJava.c21.c7;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/7/18
 */
 class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;

    private BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }
}