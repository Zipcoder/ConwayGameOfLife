package com.zipcodeconway;

public class ConwayGameOfLife {
    private SimpleWindow displayWindow;
    private int[][] startMatrix;
    private int nbRow;
    private int nbCol;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.startMatrix = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] start = new int[dimension][dimension];
        for (int row = 0; row < start.length; row++) {
             for(int col = 0; col < start[row].length ; col++){
                 int num = (int) Math.round(Math.random());
                 start[row][col] = num;
             }
        }

        return start;
    }

    public int[][] simulate(Integer maxGenerations) {

        for(int i = 0; i < maxGenerations; i++) {
        //

        }
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
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


        return 0;
    }

    private int [] getNeightbors(int row, int col, int[][] world) {
        int[] neighbors = new int[8];
        //checkNegtiveBounds
    }

        public void checkOutOfBounds(int row, int col, int length){
            if (row - 1 < 0) {
                nbRow = length;
            } else if (col - 1 < 0) {
                nbCol = length;
            }
            //checkNegtiveBounds
            if (row + 1 >) {
                row = length;
            } else if (col - 1 < 0) {
                //col = length
            }
        }

    }
}
