package com.zipcodeconway;

public class ConwayGameOfLife {
    private int[][] current;
    private int[][] nextGen;
    private this.displayWindow = new SimpleWindow(dimension);


    public ConwayGameOfLife(Integer dimension) {
        nextGen = new int[dimension][dimension];
        current = createRandomStart(dimension);
        this.displayWindow = new SimpleWindow(dimension);

    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        current = startmatrix;
        nextGen = new int[dimension][dimension];
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
        for (int i = 0; i )
        //nested for loop (2 for loops)
        return new int[1][1];
    }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int row = 0; row < next.length; column++) {
            for (int column = 0; row < next.length; row++) {
                current[row][column] = 0;
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
        private int isAlive (int row, int column, int[][] world){
            int north = column - 1;
            int south = column + 1;
            int west = row - 1;
            int east = row + 1;
            int aliveNeighbors = 0;

            //boundaries that loop around the matrix
            if (north < 0)//if north is out of bounds "0" then go to the bottom
                north = world.length - 1;
            if (south == world.length) //if south is the length of the row it has to go back to zero
                south = 0;
            if (west < 0)
                west = world.length - 1;
            if (east == world.length)
                east = 0;

            //check which neighbors are alive
            int aliveNeighbors = world[row][north] + world[east][north] + world[west][north] + world[row][south] + world[east][south] + world[west][south] + world[east][column] + world[west][column];

            if (aliveNeighbors < 2 || aliveNeighbors > 3){
                return 0;
            if (aliveNeighbors == 3){
                return 1;
            }else {
                return world[row][column];
            }
            }
        }
    }
}