package com.zipcodeconway;

public class ConwayGameOfLife {

    public ConwayGameOfLife(Integer dimension) {

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {

    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int size = dimension;
        int[][] randomStart = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                randomStart[i][j] = (int)Math.round(Math.random());
            }
        }
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        //gave createRandomStart same dimension as main
        int[][] current = createRandomStart(50);
        int[][] next = new int[50][50];
        for(int i = 0; i<maxGenerations; i++) {
            for (int j = 0; j < current.length; j++) {
                for (int k = 0; k < current.length; k++) {
                    next[j][k] = isAlive(j, k, current);
                }
            }
            copyAndZeroOut(next, current);
        }
        return current;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int i = 0; i<current.length; i++){
            for(int j = 0; j<current.length; j++){
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
//still needs work, need to account for wrapping around to other side at end of rows
    private int isAlive(int row, int col, int[][] world) {
        int count;
        if(col == 0) {

        } else if(col == world.length-1){

        }
        if(world[row][col] == 0){
            count = world[row][col-1] + world[row][col+1];
            count += world [row-1][col-1] + world[row-1][col] + world[row-1][col+1];
            count += world[row+1][col-1] + world[row+1][col] + world[row+1][col+1];
            if(count == 3) world[row][col] = 1;
            else world[row][col] = 0;
        } else if(world[row][col] == 1){
            count = world[row][col-1] + world[row][col+1];
            count += world [row-1][col-1] + world[row-1][col] + world[row-1][col+1];
            count += world[row+1][col-1] + world[row+1][col] + world[row+1][col+1];
            if(count == 2 || count == 3) world[row][col] = 1;
            else world[row][col] = 0;
        }
        return world[row][col];
    }
}
