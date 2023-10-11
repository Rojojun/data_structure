package thread;

public class ThreadGCExampleV2 {
    public static void main(String[] args) {
        InnerThreadV4 threadV4 = new InnerThreadV4();
        threadV4.setDaemon(true);
        threadV4.start();

        int requiredMemory = 0;

        for (int i = 0; i < 20; i++) {
            requiredMemory = (int) (Math.random() * 10) * 20;

            if (threadV4.freeMemory() < requiredMemory || threadV4.freeMemory() < threadV4.totalMemory() * 0.4) {
                threadV4.interrupt();
            }

            threadV4.usedMemory += requiredMemory;
            System.out.println("UsedMemory : " + threadV4.usedMemory);
        }
    }
}

class InnerThreadV4 extends Thread {
    final static int MAX_MEMORY = 1000;
    int usedMemory = 0;

    public void run() {
        while(true) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt()");
            }

            gc();
            System.out.println("Garbage collected. Free Memory : " + freeMemory());
        }
    }

    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }

    public int totalMemory() {
        return MAX_MEMORY;
    }
    public int freeMemory() {
        return MAX_MEMORY - usedMemory;
    }
}
