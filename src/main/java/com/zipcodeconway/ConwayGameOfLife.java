package com.zipcodeconway;

public class ConwayGameOfLife {

    private int [][] currentGen;
    private int [][] nextGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        currentGen = createRandomStart(dimension);
        nextGen = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
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
        return new int[1][1];
    }

    public int[][] simulate(Integer maxGenerations) {
        return new int[1][1];
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {

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
        int cellValue = world[row][col];
        int countOfAlive = 0;
        int [] neighborsValues = getNeighborsArray(row, col, world);
            for (int i = 0; i < neighborsValues.length; i++){
            if (neighborsValues[i] == 1){
                countOfAlive++;
            }
        }
        return checkForNextGen(countOfAlive, cellValue);
    }
    public int checkForNextGen(int countOfLiveNeighbors, int cellValue){
        if(countOfLiveNeighbors < 2 || countOfLiveNeighbors > 3) {
           cellValue = 0;
        }
        else if
            (countOfLiveNeighbors == 3){
                cellValue = 1;
            }
        return cellValue;
    }

    public int[] getNeighborsArray(int row, int col, int [][] world){
        int [] neighborArray = new int[8];
        neighborArray[0] = getNeighborValue(row, col - 1, world);
        neighborArray[1] = getNeighborValue(row + 1, col - 1, world);
        neighborArray[2] = getNeighborValue(row - 1, col, world);
        neighborArray[3] = getNeighborValue(row + 1, col, world);
        neighborArray[4] = getNeighborValue(row - 1, col + 1, world);
        neighborArray[5] = getNeighborValue(row, col + 1, world);
        neighborArray[6] = getNeighborValue(row + 1, col + 1, world);
        neighborArray[7] = getNeighborValue(row - 1, col - 1, world);

        return neighborArray;
    }

    public int getNeighborValue (int row, int col, int[][] world){
        row = checkRowBoundary(row, col, world.length - 1);
        col = checkColBoundary(row, col, world.length - 1);
        return world [row][col];
    }

    public int checkRowBoundary (int row, int col, int length){
        int rowBounds = 0;
        if (row < 0){
            rowBounds = length;
        }
        if (row > length){
            rowBounds = 0;
        }

        return rowBounds;
    }

    public int checkColBoundary(int row, int col, int length) {
        int colBounds = 0;
        if (col > length) {
            colBounds = 0;
        }
        if (col < 0) {
            colBounds = length;
        }

        return colBounds;
    }

}
