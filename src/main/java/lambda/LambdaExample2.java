package lambda;

@FunctionalInterface
interface MyLambda {
    void myMethod();
}

public class LambdaExample2 {
    public static void main(String[] args) {
        MyLambda myLambda = () -> {};
        Object object = (MyLambda) (() -> {});
        String str = ((Object) (MyLambda) (() -> {})).toString();

        System.out.println(myLambda);
        System.out.println(object);
        System.out.println(str);
    }
}
