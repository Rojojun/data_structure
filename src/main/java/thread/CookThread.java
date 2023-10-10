package thread;

import java.lang.annotation.Target;
import java.util.ArrayList;

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            String name = Thread.currentThread().getName();
            System.out.println(name + " ate a " + food);
        }
    }
}

class Cook implements Runnable {
    private Table table;
    Cook(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Table {
    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
        while (dishes.size() >= MAX_FOOD) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " is waiting.");

            try {
                wait(); // COOK 쓰레드를 기다리게함
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
        dishes.add(dish);
        notify(); // Customer Thread를 깨움
        System.out.println("Dishes : " + dishes.toString());
    }

    public void remove(String dishName) {
        synchronized (this) {
            String name = Thread.currentThread().getName();

            while (dishes.size() == 0) {
                System.out.println(name + "is waiting.");
                try {
                    wait(); // Customer Thread가 기다리게함
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify(); // COOK 쓰레드를 깨움
                        return;
                    }
                }

                try {
                    System.out.println(name + " is waiting.");
                    wait(); // 원하는 음식이 없으면 Customer 쓰레드를 기다리게함
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
        }
    }

    public int dishNum() {return dishNames.length;}
}

public class CookThread {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();

        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "donut"), "A").start();
        new Thread(new Customer(table, "burger"), "B").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}
