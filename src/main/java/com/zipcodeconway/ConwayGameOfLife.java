package com.zipcodeconway;

import java.util.Random;

public class ConwayGameOfLife {

    private SimpleWindow window;
    private int[][] currentGeneration;
    private int[][] nextGeneration;

    public ConwayGameOfLife(Integer dimension) {
        this.window = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.window = new SimpleWindow(dimension);
        this.currentGeneration = startMatrix;
        this.nextGeneration = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        sim.simulate(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randoStarto = new int[dimension][dimension];
        for (int i = 0; i < randoStarto.length; i++) {
            for (int j = 0; j < randoStarto[i].length; j++) {
                randoStarto[i][j] = new Random().nextInt(2);
            }
        }
        return randoStarto;
    }

    public int[][] simulate(Integer maxGenerations) {
        int count = 0;
        while (count <= maxGenerations) {
            this.window.display(currentGeneration, maxGenerations);
            for (int i = 0; i < currentGeneration.length; i++) {
                for (int j = 0; j < currentGeneration[i].length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            this.window.sleep(125);
            count++;
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int i = 0; i < current.length; i++){
            for (int j = 0; j < current[i].length; j++){
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
        return 0;
    }
}
