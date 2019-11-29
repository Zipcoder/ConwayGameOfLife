package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ConwayGameOfLife {

    private int dimension;
    private int[][] world;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife() {

    }

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.dimension = dimension;
        this.world = createRandomStart(this.dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.dimension = dimension;
        this.world = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    public int[][] createRandomStart(Integer dimension) {
        int[][] array = new int[dimension][dimension];
        IntStream.range(0,dimension)
            .forEach(row -> IntStream.range(0,dimension)
            .forEach(col -> array[col][row] = new Random().nextInt(2)));
        return array;
    }

    public int[][] simulate(Integer maxGenerations) {
        int[][] current = this.world;
        int[][] next = new int[this.dimension][this.dimension];
        for (int gen = 1; gen < maxGenerations; gen++) {
            this.displayWindow.display(current, gen);

            for (int i = 0; i < this.dimension; i++) {
                for (int j = 0; j < this.dimension; j++) {
                    next[i][j] = isAlive(i,j,current);
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
        for (int i = 0; i < next[0].length; i++) {
            current[i] = Arrays.copyOf(next[i],next[i].length);
            Arrays.fill(next[i],0);
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
    public int isAlive(int row, int col, int[][] world) {
        int dim = world[0].length;
        int numNeighbors =
                world[row][(col+1)%dim] + world[row][(dim+col-1)%dim] +
                world[(row+1)%dim][col] + world[(dim+row-1)%dim][col] +
                world[(row+1)%dim][(col+1)%dim] + world[(dim+row-1)%dim][(col+1)%dim] +
                world[(row+1)%dim][(dim+col-1)%dim] + world[(dim+row-1)%dim][(dim+col-1)%dim];

        if (world[row][col] == 1) {
            if (numNeighbors == 3 || numNeighbors == 2) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (numNeighbors == 3) {
                return 1;
            }
        }
        return world[row][col];
    }


    public void printMatrix(int[][] array) {
        for (int[] row : array) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("");
        }
    }
}
