package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;

public class ConwayGameOfLife {
    private static final int fDEFAULT_GAME_SIZE = 50;
    private int size;
    private int[][] startingGen; //maybe not need
    private SimpleWindow app;

    public ConwayGameOfLife(Integer dimension) {
        size = dimension;
        startingGen = createRandomStart(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        size = dimension;
        startingGen = startmatrix;
    }

    public void setApp(SimpleWindow app) {
        this.app = app;
    }

    public static void main(String[] args) {
        int gameSize = fDEFAULT_GAME_SIZE;

        ConwayGameOfLife sim = new ConwayGameOfLife(gameSize);
        SimpleWindow app = new SimpleWindow(gameSize);
        sim.setApp(app);

        int[][] endingWorld = sim.simulate(gameSize);
        app.display(endingWorld, gameSize);
        //app.sleep(30000);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomWorld = new int[dimension][dimension];
        Random rand = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                randomWorld[i][j] = (rand.nextInt(100) > 70) ? 1 : 0;
            }
        }
        return randomWorld;
    }

    public int[][] simulate(Integer maxGenerations) {
        int[][] currentGen = startingGen;
        int[][] nextGen = new int[size][size];

        for (int gen = 0; gen <= maxGenerations; gen++) {
            app.display(currentGen, gen);
            app.sleep(100);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    nextGen[i][j] = isAlive(i, j, currentGen);
                }
            }
            copyAndZeroOut(nextGen, currentGen);
        }
        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int i = 0; i < current.length; i++) {
            System.arraycopy(next[i], 0, current[i], 0, next[i].length);
            Arrays.fill(next[i], 0);
        }
    }

	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int liveNeighbors = getLeft(row, col, world) +
                getRight(row, col, world) +
                getTop(row, col, world) +
                getBottom(row, col, world);

        switch (liveNeighbors) {
            case 2:
                return world[row][col];
            case 3:
                return 1;
            default:
                return 0;
        }
    }

    // return 1 if the cell to the left is alive, 0 if not
    private int getLeft(int row, int col, int[][] world) {
        int lC = (col == 0) ? (size - 1) : (col - 1);
        return world[row][lC];
    }

    // return 1 if the cell to the right is alive, 0 if not
    private int getRight(int row, int col, int[][] world) {
        int rC = (col == (size - 1)) ? 0 : (col + 1);
        return world[row][rC];
    }

    //return sum of the three cells above
    private int getTop(int row, int col, int[][] world) {
        int tR = (row == 0) ? (size - 1) : (row - 1);
        return world[tR][col] + getLeft(tR, col, world) + getRight(tR, col, world);
    }

    //return sum of the three cells below
    private int getBottom(int row, int col, int[][] world) {
        int bR = (row == (size - 1)) ? 0 : (row + 1);
        return world[bR][col] + getLeft(bR, col, world) + getRight(bR, col, world);
    }
}
