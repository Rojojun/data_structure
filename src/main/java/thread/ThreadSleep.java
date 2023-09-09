package thread;

public class ThreadSleep {
    public static void main(String[] args) {
        ThreadSleepInner_1 threadSleep1 = new ThreadSleepInner_1();
        ThreadSleepInner_2 threadSleep2 = new ThreadSleepInner_2();

        threadSleep1.start();
        threadSleep2.start();

        try {
            threadSleep1.sleep(2000); // main에 적용되기 때문에 -> ThreadSleepInner_1.sleep(2000);
        } catch (InterruptedException e){}

        System.out.println("<<main 종료>>");
    }
}
class ThreadSleepInner_1 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.println("-");
        }
        System.out.println("<<th1 종료>>");
    }
}

class ThreadSleepInner_2 extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++)
            System.out.println("|");
        System.out.println("<<th2 종료>>");
    }
}
