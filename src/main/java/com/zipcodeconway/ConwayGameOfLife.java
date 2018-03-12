package com.zipcodeconway;

public class ConwayGameOfLife {

    public ConwayGameOfLife(Integer dimension) {

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {

    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int size = dimension;
        int[][] randomStart = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                randomStart[i][j] = (int)Math.round(Math.random());
            }
        }
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        //gave createRandomStart same dimension as main
        int[][] current = createRandomStart(50);
        int[][] next = new int[50][50];
        for(int i = 0; i<maxGenerations; i++) {
            for (int j = 0; j < current.length; j++) {
                for (int k = 0; k < current[i].length; k++) {
                    current[j][k] = isAlive(j, k, current);
                }
            }
            copyAndZeroOut(next, current);
        }
        return current;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int i = 0; i<current.length; i++){
            for(int j = 0; j<current[i].length; j++){
                current[i][j] = next[i][j];
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
        int rowTop;
        int rowBottom;
        if (row == 0) {
            rowTop = world.length - 1;
            rowBottom = row - 1;
        } else if (row == (world.length - 1)) {
            rowTop = row + 1;
            rowBottom = 0;
        } else {
            rowTop = row + 1;
            rowBottom = row - 1;
        }
        int colRight;
        int colLeft;
        if (col == 0) {
            colRight = col + 1;
            colLeft = world[row].length - 1;
        } else if (col == (world[row].length - 1)) {
            colRight = 0;
            colLeft = col - 1;
        } else {
            colRight = col + 1;
            colLeft = col - 1;
        }
        int count;
        count = world[row][colRight] + world[row][colLeft] + world[rowTop][colLeft] + world[rowTop][col] + world[rowTop][colRight] + world[rowBottom][colLeft] + world[rowBottom][col] + world[rowBottom][colRight];
        int alive = 0;
        if (col == 0) {
            if (count == 3) alive = 1;
            else alive = 0;
        }
        if (col == 1) {
            if (count == 2 || count == 3) alive = 1;
            else alive = 0;
        }
        return alive;
    }


}
