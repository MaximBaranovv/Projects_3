package com.project.ppp;

public class Multiplication {
    private final Matrix matrix1;
    private final Matrix matrix2;

    public Multiplication(Matrix matrix1, Matrix matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    public void multiplicationAndPrint() {
        if (matrix1.getColumn() != matrix2.getRow()) {
            throw new IllegalArgumentException("the number of columns of matrix 1 is not equal to the number of rows of matrix 2");
        } else {
            int[][] resArray = new int[matrix1.getRow()][matrix2.getColumn()];
            for (int i = 0; i < matrix1.getRow(); i++) {
                for (int j = 0; j < matrix2.getColumn(); j++) {
                    for (int k = 0; k < matrix2.getRow(); k++)
                        resArray[i][j] += matrix1.getArray()[i][k] * matrix2.getArray()[k][j];
                }
            }
            for (int i = 0; i < matrix1.getRow(); i++) {
                for (int j = 0; j < matrix2.getColumn(); j++)
                    System.out.print(resArray[i][j] + " ");
                System.out.println();
            }
        }
    }
}
