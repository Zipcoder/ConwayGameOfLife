package com.zipcodeconway;

public class ConwayGameOfLife {
    private static final int fDEFAULT_GAME_SIZE = 50;
    private int size;
    private int[][] startingGen; //maybe not need

    public ConwayGameOfLife(Integer dimension) {
        size = dimension;
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        size = dimension;
        copyAndZeroOut(startingGen, startmatrix);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(fDEFAULT_GAME_SIZE);
        int[][] endingWorld = sim.simulate(fDEFAULT_GAME_SIZE);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        return new int[1][1];
    }

    public int[][] simulate(Integer maxGenerations) {
        int[][] currentGen = new int[1][1];
        copyAndZeroOut(startingGen, currentGen);

        int[][] nextGen = new int[size][size];

        for (int gen = 0; gen < maxGenerations; gen++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                     nextGen[i][j] = isAlive(i, j, currentGen);
                }
            }
            copyAndZeroOut(currentGen, nextGen);
        }
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
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
        int liveNeighbors =  getLeft(row, col, world) +
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
        int lC = (col == 0) ? size - 1 : (col - 1);
        return world[row][lC];
    }

    // return 1 if the cell to the right is alive, 0 if not
    private int getRight(int row, int col, int[][] world) {
        int rC = (col == size - 1) ? 0 : (row + 1);
        return world[rC][col];
    }

    private int getTop(int row, int col, int[][] world) {
        int tR = (row == 0) ? size - 1 : (row - 1);
        return world[tR][col] + getLeft(tR, col, world) + getRight(tR, col, world);
    }

    private int getBottom(int row, int col, int[][] world) {
        int bR = (row == size - 1) ? 0 : (row + 1);
        return world[bR][col] + getLeft(bR, col, world) + getRight(bR, col, world);
    }
}
