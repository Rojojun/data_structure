package lambda;

import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample7 {
    public static void main(String[] args) {
        Function<String, Integer> f = (s) -> Integer.parseInt(s, 16);
        Function<Integer, String> g = (i) -> Integer.toBinaryString(i);

        Function<String, String> h = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);

        System.out.println(h.apply("FF"));
        System.out.println(h2.apply(2));

        Function<String, String> f2 = x -> x; // 항등함수
        System.out.println(f2.apply("AAA"));

        Predicate<Integer> p = i -> i < 100;

    }
}
