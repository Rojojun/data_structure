package lambda;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaExample8 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("A", 3, 300),
                new Student("B", 1, 200),
                new Student("C", 2, 100),
                new Student("D", 3, 300),
                new Student("E", 1, 200),
                new Student("F", 2, 100),
                new Student("G", 2, 100)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);


        System.out.println("--------------------------------------------------------------------------------");
        File[] fileArr = {
                new File("EX1.java"), new File("EX1.bak"),new File("EX2.java"), new File("EX2"), new File("EX2.txt")
        };

        Stream<File> fileStream = Stream.of(fileArr);
        Stream<String> filenameStream = fileStream.map(File::getName);
        filenameStream.forEach(System.out::println);

        fileStream = Stream.of(fileArr);

        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1)
                .map(s -> s.substring(s.indexOf('.') + 1))
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::print);
        System.out.println("--------------------------------------------------------------------------------");

        IntStream intStream = studentStream.mapToInt(Student::getTotalScore);

        IntSummaryStatistics stat = intStream.summaryStatistics();
        System.out.println("count=" + stat.getCount());
        System.out.println("count=" + stat.getSum());
        System.out.println("average=%2.f%n" + stat.getAverage());
        System.out.println("min=" + stat.getMin());
        System.out.println("max=" + stat.getMax());
    }
}

class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    public Student(String name, int ban, int totalScore) {
        this.name = name;
        this.ban = ban;
        this.totalScore = totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore);
    }

    String getName() {
        return name;
    }

    int getBan() {
        return ban;
    }

    int getTotalScore() {
        return totalScore;
    }

    @Override
    public int compareTo(@NotNull Student o) {
        return o.totalScore = this.totalScore;
    }
}