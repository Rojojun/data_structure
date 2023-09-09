package thread;

import javax.swing.*;

public class ThreadExampleOfCanceling2 {
    public static void main(String[] args) {
        ThreadInnerExampleV2 threadInnerExample = new ThreadInnerExampleV2();
        threadInnerExample.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력해주세요.");
        System.out.println("입력한 값은 : " + input);
        threadInnerExample.interrupt();
        System.out.println("isInterrupted():" + threadInnerExample.isInterrupted());
    }
}

class ThreadInnerExampleV2 extends Thread {
    public void run() {
        int i = 10;

        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        System.out.println("Count is finished");
    }
}