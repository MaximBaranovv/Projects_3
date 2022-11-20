package com.project.ppp;

public class Plant implements Runnable {
    private final Warehouse warehouse;

    public Plant(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            if (warehouse.getComputerCount() >= 20) {
                return;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            warehouse.put();
        }
    }
}