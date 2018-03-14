package com.zipcodeconway;

import org.omg.PortableInterceptor.INACTIVE;

public class ConwayGameOfLife {
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.currentGeneration = startmatrix;
        this.displayWindow = new SimpleWindow(dimension);
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
        int [][] random = new int[dimension][dimension];
        for (int i =0; i <random.length; i++){
            for (int j =0; j < random.length; j++){
                random[i][j] = (int) (Math.random() * 2);
            }
        }
        return random;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generations =0;
        //Count is going to be less than or Equal to maxGen b/c it has to be equal to the number of times it runs
        while (generations <= maxGenerations){
            // new display
            displayWindow.display(currentGeneration,generations);
            //now we are updating the currentGeneration to nextGeneration
            for (int row =0; row <currentGeneration.length; row++){
                for (int column = 0; column < currentGeneration[row].length; column++){
                    nextGeneration[row][column] = isAlive(row,column,currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration,currentGeneration);
            displayWindow.sleep(125);
            generations++;
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for (int row =0; row < next.length; row++){
            for (int column =0; column < current[row].length; column++){
                current[row][column] = next[row][column];
                next[row][column] =0;
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
       // set instance variables to figure out the location around cell
       int north = col - 1;
       int south = col + 1;
       int east = row + 1;
       int west = row - 1;
       int count = 0;

       //boundaries loops around if it is against the wall
        if (north < 0){
            north = world[row].length - 1;
        }if (south == world[row].length){
            south = 0;
        }if(east == world[col].length){
            east =0;
        }if (west <0){
            west = world[col].length -1;
        }

        //now checking to see how many cells around it are alive
        if (world[east][north] == 1){
            count++;
        }if (world[east][south] == 1){
            count++;
        } if (world[east][col] == 1){
            count++;
        }

        if (world[west][north] == 1){
            count++;
        }if (world[west][south] == 1){
            count++;
        }if (world[west][col] == 1){
            count++;
        }

        if (world[row][north] == 1){
            count++;
        }if (world[row][south] == 1){
            count++;
        }
        // underpopulated
        if (count < 2 || count > 3){
            return 0;
            // 3 will bring it to life
        }else if (count == 3){
            return 1;
        } else{
            return world[row][col];
        }





    }
}
