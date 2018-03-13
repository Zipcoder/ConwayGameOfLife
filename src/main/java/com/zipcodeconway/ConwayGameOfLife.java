package com.zipcodeconway;

public class ConwayGameOfLife {
    private int[][] currentGen;
    private int[][] nextGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) { //this board will start with random dimensions
        nextGen = new int[dimension][dimension];
        currentGen = createRandomStart(dimension);
        this.displayWindow = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) { //this is a pre-determined board
        currentGen = startmatrix;
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
        for (int row = 0; row < dimension; row++) {
            for (int column = 0; column < dimension; column++) { //dimensions have the same length both ways
                currentGen[row][column] = (int) (Math.random() * 2); //creates random starting board
            }
        }
        return currentGen;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generation = 0;
        while (generation <= maxGenerations) {
            this.displayWindow.display(currentGen, generation);
            for (int goku = 0; goku < currentGen.length; goku++) {
                for (int freeza = 0; freeza < currentGen.length; freeza++) {
                    nextGen[goku][freeza] = isAlive(goku, freeza, currentGen);
                }
            }
            copyAndZeroOut(nextGen, currentGen);
            this.displayWindow.sleep(125);
            generation++;
        }
        return currentGen;
    }
        // copy the values of 'next' matrix to 'current' matrix,
        // and then zero out the contents of 'next' matrix
        private void copyAndZeroOut (int[][] nextGen, int[][] current){
            for (int row = 0; row < nextGen.length; row++) {
                for (int column = 0; column < nextGen.length; column++) {
                    current[row][column] = nextGen[row][column];
                    nextGen[row][column] = 0;
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
            private int isAlive ( int row, int column, int[][] world){ //check which neighbors are alive
                int north = column - 1;
                int south = column + 1;
                int west = row - 1;
                int east = row + 1;
                //boundaries that loop around the matrix
                if (north < 0)//if north is out of bounds "0" then go to the bottom
                    north = world[row].length - 1;
                if (south == world[row].length) //if south is the length of the row it has to go back to zero
                    south = 0;
                if (west < 0)
                    west = world[column].length - 1;
                if (east == world[column].length)
                    east = 0;
                int aliveNeighbors = world[row][north] + world[east][north] + world[west][north] + world[row][south]
                        + world[east][south] + world[west][south] + world[east][column] + world[west][column];
                if (aliveNeighbors < 2 || aliveNeighbors > 3)
                    return 0;
                    if (aliveNeighbors == 3) {
                        return 1;
                    } else {
                        return world[row][column];
                    }
                }
            }