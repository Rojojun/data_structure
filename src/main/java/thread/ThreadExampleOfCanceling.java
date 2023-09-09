package thread;

import javax.swing.*;

public class ThreadExampleOfCanceling {
    public static void main(String[] args) {
        ThreadInnerExample threadInnerExample = new ThreadInnerExample();
        threadInnerExample.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력해주세요.");
        System.out.println("입력한 값은 : " + input);
        threadInnerExample.interrupt();
        System.out.println("isInterrupted():" + threadInnerExample.isInterrupted());
    }
}

class ThreadInnerExample extends Thread {
    public void run() {
        int i = 10;

        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long x = 0; x < 2500000000L; x++);
        }
        System.out.println("count is terminated");
    }
}