package com.zipcodeconway;

public class ConwayGameOfLife {

    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        currentGeneration = createRandomStart(dimension);
        nextGeneration = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        currentGeneration = startmatrix;
        nextGeneration = new int[dimension][dimension];
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
        int[][] randomStart = new int[dimension][dimension];
        for(int i = 0; i<dimension; i++){
            for(int j = 0; j<dimension; j++){
                randomStart[i][j] = (int)Math.round(Math.random());
            }
        }
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        int count = 0;
        //count has to be less than OR equal to maxGen because it has to be equal to the number of times it has to run
        while (count <= maxGenerations) {
            displayWindow.display(currentGeneration, count);
            //update nextGen from currentGen
            for (int row = 0; row < currentGeneration.length; row++) {
                for (int column = 0; column < currentGeneration[row].length; column++) {
                    nextGeneration[row][column] = isAlive(row, column, currentGeneration);
                }
            }

            copyAndZeroOut(nextGeneration, currentGeneration);
            displayWindow.sleep(125);
            count++;
        }
        return currentGeneration;

    }


        // copy the values of 'next' matrix to 'current' matrix,
        // and then zero out the contents of 'next' matrix
        public void copyAndZeroOut ( int[][] next, int[][] current){
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
        private int isAlive(int row, int col, int[][] world){
            int north = col - 1;
            int south = col + 1;
            int east = row + 1;
            int west = row - 1;

            if (north == -1) {
                north = world[row].length - 1;
            }
            if (south == world[row].length) {
                south = 0;
            }
            if (west == -1) {
                west = world[col].length - 1;
            }
            if (east == world[col].length) {
                east = 0;
            }

            int numberOfLiveNeighbors = world[row][north] + world[east][north] + world[west][north]
                    + world[row][south] + world[east][south] + world[west][south]
                    + world[east][col] + world[west][col];

            if (numberOfLiveNeighbors == 3){
                return 1;
            }
            if (numberOfLiveNeighbors < 2){
                return 0;
            }
            if (numberOfLiveNeighbors > 3){
                return 0;
            }
            return world[row][col];
        }
    }
