package com.project.ppp;

public class RunnableImpl implements Runnable {
    private final Multiplication multiplication;

    public RunnableImpl(Multiplication multiplication) {
        this.multiplication = multiplication;
    }

    @Override
    public void run() {
        synchronized (multiplication) {
            for (int i = 1; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
                multiplication.multiplicationAndPrint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        }
    }
}