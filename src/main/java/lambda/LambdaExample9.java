package lambda;

import java.util.Arrays;
import java.util.stream.Stream;

public class LambdaExample9 {
    public static void main(String[] args) {
        Stream<String[]> strArrStrm = Stream.of(
                new String[] {"abc", "def", "jkl"},
                new String[] {"ABC", "DEF", "JKL"}
        );

        Stream<String> stringStream = strArrStrm.flatMap(Arrays::stream);

        stringStream.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
                "Believe or not It is ture",
                "Do or do not There is no try",
        };
        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        Stream<String> strSteam1 = Stream.of("AAA", "ABC", "bBb", "Dd");
        Stream<String> strSteam2 = Stream.of("bbb", "aaa", "ccc", "dd");

        Stream<Stream<String>> streamStream = Stream.of(strSteam1, strSteam2);
        Stream<String> stream = streamStream
                .map(s -> s.toArray(String[]::new))
                .flatMap(Arrays::stream);

        stream.map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);
    }
}
