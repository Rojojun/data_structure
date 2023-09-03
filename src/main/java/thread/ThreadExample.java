package thread;

public class ThreadExample {
    public static void main(String[] args) {
        InnerThread iThread = new InnerThread();

        Runnable r = new InnerRunnable();
        Thread thread = new Thread(r);
        // = Thread thread = new Thread(new InnerRunnable());

        iThread.start();
        thread.start();
    }
}

class InnerThread extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(getName());
        }
    }
}

class InnerRunnable implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i ++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
