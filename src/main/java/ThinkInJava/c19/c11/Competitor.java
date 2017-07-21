package ThinkInJava.c19.c11;

public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
