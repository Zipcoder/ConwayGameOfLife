package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;

public class ConwayGameOfLife {

    private Integer dimension;
    private int[][] startMatrix;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
    }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.dimension = dimension;
        this.startMatrix = startMatrix;
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomArray = new int[dimension][dimension];

        Random random = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray[i].length; j++) {
                int zeroOrOne = random.nextInt(2);
                randomArray[i][j] = zeroOrOne;
            }
        }
        return randomArray;
    }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
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
        return 0;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);

//        int[][] testArray3 = sim.createRandomStart(5);
//
//        for (int i = 0; i < testArray3.length; i++) {
//            System.out.println("row " + i + ": " + Arrays.toString(testArray3[i]));
//        }

        int[][] currentArray = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0} };
        int[][] nextArray = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0} };
    }

}
