package com.zipcodeconway;

public class ConwayGameOfLife {

    int edge;
    int[][] currentGen;
    int[][] nextGen;
    SimpleWindow displayWindow;


    public ConwayGameOfLife(Integer dimension) {
        edge = dimension - 1;
        nextGen = new int[dimension][dimension];
        displayWindow = new SimpleWindow(dimension);
        currentGen = createRandomStart(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        edge = dimension - 1;
        currentGen = startmatrix;
        nextGen = new int[dimension][dimension];
        displayWindow = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {

        currentGen = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ((Math.random() * 100) > 28) {
                    currentGen[i][j] = 0;
                } else {
                    currentGen[i][j] = 1;
                }
            }
        }

        return currentGen;
    }

    public int[][] simulate(Integer maxGenerations) {

        int generations = 0;

        while (generations <= maxGenerations) {
            this.displayWindow.display(currentGen, generations);
            this.displayWindow.sleep(75);
            copyAndZeroOut(nextGen, currentGen);
            generations++;
        }

        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {

        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j< current.length; j++) {
                next[i][j] = isAlive(i, j, current);
            }
        }

        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current.length; j++) {
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
    protected int isAlive(int row, int col, int[][] world) {

        int iLooker;
        int jLooker;
        int count = 0;

        for (int i = row-1; i < row+2; i++) {

            if (i > edge) {
                iLooker = 0;
            } else if (i < 0) {
                iLooker = edge;
            } else {
                iLooker = i;
            }

            for (int j = col-1; j < col+2; j++) {

                if (j > edge) {
                    jLooker = 0;
                } else if (j < 0) {
                    jLooker = edge;
                } else {
                    jLooker = j;
                }

                if (world[iLooker][jLooker] == 1) {
                    count++;
                }
            }
        }

        if (count == 3) {
            return 1;
        } else if (count <3 || count > 4) {
            return 0;
        }

        return world[row][col];
    }


}
