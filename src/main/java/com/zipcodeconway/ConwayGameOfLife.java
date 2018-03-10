package com.zipcodeconway;

import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.max;
import static jdk.nashorn.internal.objects.NativeMath.random;

public class ConwayGameOfLife {

    private SimpleWindow displayWindow;
    private int[][] currentGeneration;
    private int[][] nextGeneration;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = startmatrix;
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
        int[][] randomStart = new int[dimension][dimension];
        for (int row = 0; row < randomStart.length; row++){
            for (int column = 0; column < randomStart[row].length; column++){
                randomStart[row][column] = new Random().nextInt(2);
            }
        }
        return randomStart;
    }
    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int row = 0; row < current.length; row++){
            for (int column = 0; column < current[row].length; column++){
                current[row][column] = next[row][column];
                next[row][column] = 0;
            }
        }
    }
    public int[][] simulate(Integer maxGenerations) {
        int generation = 0;
        while (generation <= maxGenerations){
            this.displayWindow.display(currentGeneration, maxGenerations);
            for (int row = 0; row <currentGeneration.length; row++){
                for (int col = 0; col <currentGeneration[row].length; col++){
                    nextGeneration[row][col] = isAlive(row, col, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            this.displayWindow.sleep(125);
            generation++;
        }
        return currentGeneration;
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
        return world[row][col];
    }
}
