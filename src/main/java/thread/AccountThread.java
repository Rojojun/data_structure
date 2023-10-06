package thread;

import java.util.Random;

class AccountThread {
    public void main(String[] args) {
        Runnable runnable = new AccountRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    class Account {
        private int balance = 1000;
        public int getBalance() {
            return balance;
        }

        public synchronized void withdraw(int money) {
            if (balance >= money) {
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
                balance -= money;
            }
        }

//        위와 같은 코드
//        public void withDraw(int money) {
//            synchronized(this) {
//                if (balance >= money) {
//                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
//                    balance -= money;
//                }
//            }
//        }
    }

    class AccountRunnable implements Runnable {
        Account account = new Account();

        public void run() {
            while (account.getBalance() > 0) {
                int money = (int) (Math.random() * 3 + 1) * 100;
                account.withdraw(money);
                System.out.println("account = " + account.getBalance());
            }
        }
    }
}
