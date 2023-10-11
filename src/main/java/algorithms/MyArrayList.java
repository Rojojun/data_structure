package algorithms;

public class MyArrayList {
    public static void main(String[] args) {
        ThreadOne th1 = new ThreadOne();
        ThreadTwo th2 = new ThreadTwo();
        th1.start();
        th2.start();

        try {
            th1.sleep(2000);
        } catch (InterruptedException e) {}

        System.out.println("<<main 종료>>");
    }

    static class ThreadOne extends Thread {
        public void run() {
            for (int i = 0; i < 300; i++)
                System.out.println("-");
            System.out.println("<<th1 종료>>");
        }
    }

    static class ThreadTwo extends Thread {
        public void run() {
            for (int i = 0; i < 300; i++)
                System.out.println("|");
            System.out.println("<<th2 종료>>");
        }
    }
}
