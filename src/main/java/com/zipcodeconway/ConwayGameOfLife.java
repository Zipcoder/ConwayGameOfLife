package com.zipcodeconway;

import java.util.Arrays;

public class ConwayGameOfLife {
    private Integer dimension;
    private int[][] current;
    private int[][] next;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.current = createRandomStart(dimension);
        this.next= new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.current = startmatrix;
        this.dimension = dimension;
        this.next= new int[dimension][dimension];
        //this.next = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] start = new int[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                int val = (int) Math.round(Math.random()*0.75);
                start[row][col] = val;
            }
        }
        return start;
    }

    public int[][] simulate(Integer maxGenerations) {
        int[][] currentWorld = current;
        int[][] nextWorld = next;

        for (int i = 0; i <= maxGenerations; i++) {
            this.displayWindow.display(currentWorld, i);

            nextWorld = runSim(currentWorld, nextWorld); // tested!!!
            currentWorld = copyAndZeroOut(nextWorld, currentWorld);

        }
        this.displayWindow.sleep(125);
        //displayWindow.sleep(10000);
        return currentWorld;
    }

    private int[][] runSim(int[][] currentWorld, int[][] nextWorld) {
        for (int row = 0; row < currentWorld.length; row++) {
            for (int col = 0; col < currentWorld[row].length; col++) {
                nextWorld[row][col] = isAlive(row, col, currentWorld);
            }
        }
        return nextWorld;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public int[][] copyAndZeroOut(int[][] nextWorld, int[][] currentWorld) {
        for (int i = 0; i < currentWorld.length; i++) {
            System.arraycopy(nextWorld[i], 0, currentWorld[i], 0, currentWorld.length);
            Arrays.fill(nextWorld[i], 0);
        }
        return currentWorld;
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    public int isAlive(int row, int col, int[][] world) {
        int neighbours = getNeighbours(row, col, world);

        //test
        if (neighbours == 2)
            return world[row][col];
        else if (neighbours == 3)
            return 1;
        else
            return 0;
    }

    public int getNeighbours(int row, int col, int[][] input) {
        int totalNeighbors = 0;
        int index = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighbourRow = getNeighbourRow(i, row, input);
                int neighbourCol = getNeighbourCol(j, col, input);
                if (!(getNeighbourRow(i, row, input) == row && getNeighbourCol(j, col, input) == col)) {
                    totalNeighbors += input[neighbourRow][neighbourCol];
                    index++;
                }
            }
        }
        return totalNeighbors;
    }

    public int getNeighbourRow(int x, int row, int[][] input) {
        int neighbourRow;
        if (row + x < 0) {
            neighbourRow = input.length - 1;
        } else if (row + x > input.length - 1) {
            neighbourRow = 0;
        } else {
            neighbourRow = row + x;
        }
        return neighbourRow;
    }

    public int getNeighbourCol(int x, int col, int[][] input) {
        int neighbourCol;
        if (col + x < 0) {
            neighbourCol = input[0].length - 1;
        } else if (col + x > input[0].length - 1) {
            neighbourCol = 0;
        } else {
            neighbourCol = col + x;
        }
        return neighbourCol;
    }
}
