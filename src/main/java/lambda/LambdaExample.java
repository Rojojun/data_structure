package lambda;

@FunctionalInterface
interface LambdaInterface {
    void run();
}

public class LambdaExample {
    static void execute(LambdaInterface lambdaInterface) {
        lambdaInterface.run();
    }

    static LambdaInterface getLambdaInterface() {
        LambdaInterface lambdaInterface = () -> System.out.println("lambdaInterface3.run()");
        return lambdaInterface;
    }

    public static void main(String[] args) {
        LambdaInterface lambdaInterface1 = () -> System.out.println("lambdaInterface1.run()");

        LambdaInterface lambdaInterface2 = new LambdaInterface() {
            @Override
            public void run() {
                System.out.println("lambdaInterface2.run()");
            }
        };

        LambdaInterface lambdaInterface3 = getLambdaInterface();
        lambdaInterface1.run();
        lambdaInterface2.run();
        lambdaInterface3.run();

        execute(lambdaInterface1);
        execute(() -> System.out.println("run()"));
    }
}
