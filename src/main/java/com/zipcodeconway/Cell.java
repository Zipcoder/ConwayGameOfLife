package com.zipcodeconway;

/**
 * filename:
 * project: ConwayGameOfLife
 * author: https://github.com/vvmk
 * date: 3/9/18
 */
public class Cell {
    private int row;
    private int col;
    private boolean living;

    public Cell(int row, int col, boolean living) {
        this.row = row;
        this.col = col;
    }

    public boolean living() {
        return living;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }
}
