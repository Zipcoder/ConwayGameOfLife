package com.zipcodeconway;

import java.util.Arrays;
import java.util.Collections;

public class ConwayGameOfLife {
    private SimpleWindow displayWindow;
    private int[][] current;
    private int[][] next;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.current = startmatrix;
        //System.arraycopy(createRandomStart(dimension), 0, current, 0, current.length);
        next = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    /*private int[][] createRandomStart(Integer dimension) {
        int[][] start = new int[dimension][dimension];
        for (int row = 0; row < start.length; row++) {
            for (int col = 0; col < start[row].length; col++) {
                int num = (int) Math.round(Math.random());
                start[row][col] = num;
            }
        }

        return start;
    }*/
    /*loop through generations.
    loop through current maxtrix.
    add is alive value to next matrix.
    at end of loops call copyAndZeroOut.*/

    public int[][] simulate(Integer maxGenerations) {
        for (int i = 0; i < maxGenerations; i++) {
            this.displayWindow.display(current, maxGenerations);
            for (int row = 0; row < current.length; row++) {
                for (int col = 0; col < current[row].length; col++) {
                    next[row][col] = isAlive(row, col, current);
                }
            }

            copyAndZeroOut(next, current);
            this.displayWindow.sleep(125);
        }
        return current;
    }


    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        // this.current = current;
        System.arraycopy(next, 0, current, 0, next.length);

        //zeros all indexes of next;
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[i].length; j++) {
                next[i][j] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int sumOfLife = 0;
        int isAlive = world[row][col];
        int[] neghibors = getNeightbors(row, col, world);
        for (int life : neghibors) {
            if (life == 1) sumOfLife++;
        }

        return getAlive(sumOfLife, isAlive);
    }

    private int getAlive(int neighborsAlive, int isAlive) {
        if (neighborsAlive < 2) {
            isAlive = 0;
        } else if (neighborsAlive > 3) {
            isAlive = 0;
        } else if (neighborsAlive == 2 || neighborsAlive == 3) {
            isAlive = 1;
        } else if (isAlive == 0 && neighborsAlive == 3) {
            isAlive = 1;
        }

        return isAlive;

    }

    private int[] getNeightbors(int row, int col, int[][] world) {
        int[] neighbors = new int[8];

        neighbors[0] = checkOutOfBounds(row, col - 1, world);
        neighbors[1] = checkOutOfBounds(row - 1, col - 1, world);
        neighbors[2] = checkOutOfBounds(row + 1, col - 1, world);
        neighbors[3] = checkOutOfBounds(row - 1, col, world);
        neighbors[4] = checkOutOfBounds(row + 1, col, world);
        neighbors[5] = checkOutOfBounds(row - 1, col + 1, world);
        neighbors[6] = checkOutOfBounds(row, col + 1, world);
        neighbors[7] = checkOutOfBounds(row + 1, col + 1, world);

        return neighbors;

    }

    private int checkOutOfBounds(int row, int col, int[][] world) {
        int theRow = checkRow(row, world.length -1);
        int theCol = checkColumn(col, world.length -1);
        return world[theRow][theCol];
    }

    private int checkColumn(int col, int length) {
        if (col < 0) {
            col = length;
        } else if (col > length) {
            col = 0;
        }

        return col;
    }

    private int checkRow(int row, int length) {
        if (row < 0) {
            row = length;
        } else if (row > length) {
            row = 0;
        }
        return row;
    }
}

