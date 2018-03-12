package com.zipcodeconway;

public class ConwayGameOfLife {

    private int[][] currentGen;
    private int[][] nextGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {

        this.currentGen = createRandomStart(dimension);
        this.nextGen = new int [dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {

        this.currentGen = startmatrix;
        this.nextGen = new int [dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randStart = new int[dimension][dimension];
        for (int i = 0; i < randStart.length; i++) {
            for (int j = 0; j < randStart.length; j++) {
                randStart[i][j] = (int)Math.round(Math.random());
            }
        } return randStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generations = 0;
        while (generations <= maxGenerations) {
            this.displayWindow.display(currentGen, generations);
            for (int i = 0; i < currentGen.length; i++) {
                for (int j = 0; j < currentGen.length; j++) {
                    nextGen[i][j] = isAlive(i, j, currentGen);
                }
            }
            this.displayWindow.sleep(125);
            copyAndZeroOut(nextGen, currentGen);
            generations++;
        }
        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {

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
    private int isAlive(int row, int col, int[][] world) {


        int north = col - 1;
        int south = col + 1;
        int east = row + 1;
        int west = row -1;

        if (north < 0) {
            north = world[row].length-1;
        }
        if (south == world[row].length) {
            south = 0;
        }
        if (east == world[col].length) {
            east = 0;
        }
        if (west < 0) {
            west = world[col].length - 1;
        }

        int numberOfLivingNeighbors = world[row][north] + world[row][south] + world[east][col] + world[west][col]
                + world[east][north] + world[east][south] + world[west][north] + world[west][south];

        if (numberOfLivingNeighbors > 2 && numberOfLivingNeighbors <= 3)
            return 1;
        else if (numberOfLivingNeighbors < 2 || numberOfLivingNeighbors > 3)
            return 0;
        else return world[row][col];

    }
}
