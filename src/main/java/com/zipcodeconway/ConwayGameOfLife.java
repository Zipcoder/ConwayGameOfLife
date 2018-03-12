package com.zipcodeconway;

public class ConwayGameOfLife {

    private SimpleWindow displayWindow;
    private int[][] currentGeneration;
    private int[][] nextGeneration;


    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];


    }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = startMatrix;
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
        int [][] randomStart = new int[dimension][dimension];
        for(int i = 0; i < randomStart.length; i++) {
            for(int j = 0; j < randomStart[i].length; j++) {
                randomStart[i][j] = (int)Math.round(Math.random());
            }
        }
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        int counter = 0;
        while(counter <= maxGenerations) {
            this.displayWindow.display(currentGeneration, counter);
            for(int i = 0; i < currentGeneration.length; i++) {
                for(int j = 0; j < currentGeneration[i].length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            displayWindow.sleep(125);
            counter++;
        }
        return currentGeneration;

    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int i = 0; i < current.length; i++) {
            for(int j = 0; j < current[i].length; j++) {
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
    public int isAlive(int row, int col, int[][] world) {
        int living = 0;

        int west = row - 1;
        int east = row + 1;
        int north = col - 1;
        int south = col + 1;

        if(west == -1) {
            west = world.length - 1;
        }
        if(east == world.length){
            east = 0;
        }
        if(north == -1) {
            north = world[row].length - 1;
        }
        if(south == world[row].length) {
            south = 0;
        }

        if(world[west][north] == 1) {
            living++;
        }
        if(world[west][col] == 1) {
            living++;
        }
        if(world[west][south] == 1) {
            living++;
        }
        if(world[row][north] == 1) {
            living++;
        }
        if(world[row][south] == 1) {
            living++;
        }
        if(world[east][north] == 1) {
            living++;
        }
        if(world[east][col] == 1) {
            living++;
        }
        if(world[east][south] == 1) {
            living++;
        }

        if(living < 2) {
            return 0;
        } else if (living > 3) {
            return 0;
        } else if (living == 3) {
            return 1;
        } else {
            return world[row][col];
        }
    }
}
