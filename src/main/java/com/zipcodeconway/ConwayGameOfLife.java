package com.zipcodeconway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomArray = new int[dimension][dimension];
        for (int i = 0; i < randomArray.length; i++){
            for (int j = 0; j < randomArray[i].length; j++){
                randomArray[i][j] = new Random().nextInt(2);
            }
        }
        return randomArray;
    }

    public int[][] simulate(Integer maxGenerations) {
        int count = 0;
        while (count <= maxGenerations) {
            this.displayWindow.display(currentGeneration, count);
            for (int i = 0; i < currentGeneration.length; i++) {
                for (int j = 0; j < currentGeneration[i].length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            this.displayWindow.sleep(125);
            count++;

        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int i = 0; i < current.length; i++){
            for (int j = 0; j <current[i].length; j++){
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
        int aliveNeighbors =0;
        int i2 = row - 1;
        int i3 = row + 1;
        int j2 = col - 1;
        int j3 = col + 1;
        if (i2 == -1)
            i2 = world.length - 1;
        if (i3 == world.length)
            i3 = 0;
        if (j2 == -1)
            j2 = world[row].length - 1;
        if (j3 == world[row].length)
            j3 = 0;

        if (world[i2][j2] == 1)aliveNeighbors++;
        if (world[i2][col] == 1)aliveNeighbors++;
        if (world[i2][j3] == 1)aliveNeighbors++;
        if (world[row][j2] == 1)aliveNeighbors++;
        if (world[row][j3] == 1)aliveNeighbors++;
        if (world[i3][j2] == 1)aliveNeighbors++;
        if (world[i3][col] == 1)aliveNeighbors++;
        if (world[i3][j3] == 1)aliveNeighbors++;

        if (aliveNeighbors < 2){
            return 0;
        } else if (aliveNeighbors > 3){
            return 0;
        } else if (aliveNeighbors == 3){
            return 1;
        } else {
            return world[row][col];
        }

    }
}
