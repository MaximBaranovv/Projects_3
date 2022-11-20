package com.project.ppp;

public class Consumer1 implements Runnable {
    private final Warehouse warehouse;

    public Consumer1(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            warehouse.get();
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}