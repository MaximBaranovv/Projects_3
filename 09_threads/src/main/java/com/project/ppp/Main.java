package com.project.ppp;

public class Main {
    public static void main(String[] args) {
        //row - строка
        //column - столбец
        int a[][] = { { 2, 1, 2,1},
                { 11, 12, 13, 14 },
                { 22, 23, 24,25},
                { 9, 8, 7,6 } };

        int b[][] = { { 1, 1 },
                      { 1, 1 },
                      { 0, 0 },
                      { 0, 0 } };
        int matrix1Row = 4;
        int matrix1Column = 4;
        int matrix2Row= 4;
        int matrix2Column = 2;

        Matrix matrix1 = new Matrix( matrix1Row ,matrix1Column, a);
        Matrix matrix2 = new Matrix(matrix2Row,matrix2Column, b );
        Multiplication mult = new Multiplication(matrix1, matrix2);
        for (int i = 1; i < 6; i++){
            Thread thread = new Thread(new RunnableImpl(mult));
            thread.setName("Thread "+ i);
            thread.start();
        }

        Warehouse warehouse =new Warehouse();
        Plant plant = new Plant(warehouse);
        Consumer1 consumer1 = new Consumer1(warehouse);
        Consumer2 consumer2 = new Consumer2(warehouse);
        new Thread(plant).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}
