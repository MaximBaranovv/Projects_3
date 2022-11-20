package com.project.ppp;

public class Matrix {
    private final int row;
    private final int column;
    private final int[][] array;

    public Matrix(int row, int column, int[][] array) {
        this.row = row;
        this.column = column;
        this.array = array;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int[][] getArray() {
        return array;
    }
}