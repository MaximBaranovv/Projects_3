package com.project.ppp;

public class Warehouse {
    private int computers = 0;
    private int computerCount = 0;

    public int getComputerCount() {
        return computerCount;
    }

    public synchronized void get() {
        while (computers <= 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        computers -= 2;
        System.out.println("Покупатель " + Thread.currentThread().getName() + " купил 2 компа");
        System.out.println("Компов на складе: " + computers);
        notify();
    }

    public synchronized void put() {
        System.out.println("_____________");
        computers += 3;
        System.out.println("Завод добавил 3 компа");
        System.out.println("Компов на складе: " + computers);
        computerCount += 3;
        System.out.println("За все время было произведено: " + computerCount + " компов");
        notify();
        System.out.println("_____________");
    }
}