package com.zipcodeconway;

public class ConwayGameOfLife {

    private int[][] currentGen;
    private int[][] nextGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {

        currentGen = createRandomStart(dimension);
        nextGen = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);

    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {

        currentGen = startmatrix;
        nextGen = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);

    }

    public static void main(String[] args) {

        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    public int[][] simulate(Integer maxGenerations) {

        int count = 0;
        //count has to be less than OR equal to maxGen because it has to be equal to the number of times it has to run
        while (count <= maxGenerations) {
            displayWindow.display(currentGen, count);
            //update nextGen from currentGen
            for (int row = 0; row < currentGen.length; row++) {
                for (int column = 0; column < currentGen[row].length; column++) {
                    nextGen[row][column] = isAlive(row, column, currentGen);
                }
            }

            copyAndZeroOut(nextGen, currentGen);
            displayWindow.sleep(125);
            count++;
        }
        return currentGen;
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] newRandomArray = new int[dimension][dimension];
        for (int row = 0; row < newRandomArray.length ; row++) {
            for (int column = 0; column < newRandomArray[row].length; column++) {
                newRandomArray[row][column] = (int)(Math.random() * 2);
            }
        }
        return newRandomArray;
    }


    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {

        for (int row = 0; row < current.length; row++)
            for (int column = 0; column < current[row].length; column++) {
                //Copying current to next
                current[row][column] = next[row][column];
                //wipe out next
                next[row][column] = 0;
            }
    }



    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:

    private int isAlive(int row, int col, int[][] world) {

        //set instance variables to figure out location around cell
        int north = col - 1;
        int south = col + 1;
        int east = row + 1;
        int west = row -1;
        int count = 0;


        //boundries loops around if neighbor is against wall
        if(north < 0) {
            north = world[row].length - 1;
        }
        if(south == world[row].length) {
            south = 0;
        }
        if(east == world[col].length) {
            east = 0;
        }
        if(west < 0) {
            west = world[col].length - 1;
        }

        //checking how many cells around it are alive
        //by rows first
        if(world[east][north] == 1) count++;
        if(world[east][south] == 1) count++;
        if(world[east][col] == 1) count++;

        if(world[west][north] == 1) count++;
        if(world[west][south] == 1) count++;
        if(world[west][col] == 1) count++;
        //rows
        if(world[row][north] == 1) count++;
        if(world[row][south] == 1) count++;

        //check rules and determine if it stays alive or dies.
        /*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	    */

        //underpopulation
        if(count < 2) {
            return 0;
            //overcrowding
        }else if (count > 3 ) {
            return 0;
            //3 neighbors brings it to life so = 1.
        }else if(count == 3) {
            return 1;
        }else
            //return the cell as it is JUST incase...... instead of returning 1.
            return world[row][col];

    }
}
