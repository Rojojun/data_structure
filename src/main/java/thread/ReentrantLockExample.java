package thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class CustomerV2 implements Runnable {
    private TableV2 tableV2;
    private String food;

    CustomerV2(TableV2 tableV2, String food) {
        this.tableV2 = tableV2;
        this.food = food;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            String name = Thread.currentThread().getName();

            tableV2.remove(food);
            System.out.println(name + " ate a " + food);
        }
    }
}

class CookV2 implements Runnable {
    private TableV2 tableV2;

    CookV2(TableV2 tableV2) {
        this.tableV2 = tableV2;
    }

    public void run() {
        while (true) {
            int idx = (int) (Math.random() * tableV2.dishNum());
            tableV2.add(tableV2.dishNames[idx]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }
}

class TableV2 {
    String[] dishNames = {"donut","donut","burger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCustomer = lock.newCondition();

    public void add(String dish) {
        lock.lock();

        try {
            while (dishes.size() >= MAX_FOOD) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting.");
                try {
                    forCook.await(); // wait()
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
            dishes.add(dish);
            forCustomer.signal(); // notify()
            System.out.println("dishes = " + dishes.toString());
        }
        finally {
            lock.unlock();
        }
    }

    public void remove(String dishName) {
        lock.lock();
        String name = Thread.currentThread().getName();

        try {
            while (dishes.size() == 0) {
                System.out.println("waiting name = " + name);
                try {
                    forCustomer.await(); // wait() - customer 쓰레드를 기다리게함
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if(dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        forCook.signal();
                        return;
                    }
                }
                try {
                    System.out.println("waiting name = " + name);
                    forCustomer.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        } finally {
            lock.unlock();
        }
    }

    public int dishNum() {return dishNames.length;}
}

public class ReentrantLockExample {
    public static void main(String[] args) {
        TableV2 tableV2 = new TableV2();

        new Thread(new CookV2(tableV2), "COOK").start();
        new Thread(new CustomerV2(tableV2, "donut"), "CUST1").start();
        new Thread(new CustomerV2(tableV2, "burger"), "CUST2").start();
    }
}
