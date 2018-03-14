package com.zipcodeconway;

public class ConwayGameOfLife {

    private int[][] currentGen;
    private int[][] nextGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.currentGen = createRandomStart(dimension);
        this.nextGen = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.currentGen = startMatrix;
        this.displayWindow = new SimpleWindow(dimension);
        this.nextGen = new int[dimension][dimension];
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
        for(int i = 0; i < random.length; i ++){
            for(int j = 0; j < random.length; j++){
                random[i][j] = (int) Math.round(Math.random());
            }
        }
        return random;
    }

    public int[][] simulate(Integer maxGenerations) {
        for (int i = 0; i <= maxGenerations; i++) {
            this.displayWindow.display(currentGen, i);
            for (int j = 0; j < currentGen.length; j++) {
                for (int k = 0; k < currentGen[j].length; k++) {
                    nextGen[j][k] = isAlive(j, k, currentGen);
                }
            }
            copyAndZeroOut(nextGen, currentGen);
            this.displayWindow.sleep(1000);
        }

        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix

    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[i].length; j++) {
                current[i][j] = next[i][j];
                next[i][j] = 0;
            }
        }

    }

    /*  Calculate if an individual cell should be alive in the next generation.
        Based on the game logic:
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    public int isAlive(int row, int col, int[][] world) {
        int cellValue = world[row][col];
        int countOfAlive = 0;
        Integer[] neighborsValues = getNeighborsArray(row, col, world);
        for (int i = 0; i < neighborsValues.length; i++) {
            if (neighborsValues[i] == 1) {
                countOfAlive++;
            }
        }
        return checkForNextGen(countOfAlive, cellValue);
    }

    public int checkForNextGen(int countOfLiveNeighbors, int cellValue) {
        if (countOfLiveNeighbors < 2 || countOfLiveNeighbors > 3) {
            cellValue = 0;
        } else if
                (countOfLiveNeighbors == 3) {
            cellValue = 1;
        }
        return cellValue;
    }
    /*
    in this method we are checking the rules of the game to see:
    1. if the count of our neighbors is less than 2 or greater than 3 --> it will be dead in the next gen
    2. if the count of our neighbors is equal to 2 or 3 then the cell will be alive in the next gen
    3. other
     */

    public Integer[] getNeighborsArray(int row, int col, int[][] world) {
        Integer[] neighborArray = new Integer[8];
        neighborArray[0] = getNeighborValue(row, col - 1, world); //west
        neighborArray[1] = getNeighborValue(row + 1, col - 1, world); //SW
        neighborArray[2] = getNeighborValue(row - 1, col, world); //north
        neighborArray[3] = getNeighborValue(row + 1, col, world); //south
        neighborArray[4] = getNeighborValue(row - 1, col + 1, world); //NE
        neighborArray[5] = getNeighborValue(row, col + 1, world); //east
        neighborArray[6] = getNeighborValue(row + 1, col + 1, world); //SE
        neighborArray[7] = getNeighborValue(row - 1, col - 1, world); //NW

        return neighborArray;
    }
    /*
    the above method is creating a new array that is filled with all of the neighbors' values
    we are calling the method below that will give us the values of each neighbor given the row, col, and [][]
     */

    public int getNeighborValue(int row, int col, int[][] world) {
        row = checkRowBoundary(row, world.length - 1);
        col = checkColBoundary(col, world.length - 1);
        return world[row][col];
    }
    /*
    this method is getting our neighbors value. We are using world.length - 1 bc we are going to the last element.
    when calling [row][col] we are getting a single int value --> either a 0 or 1
     */

    public int checkRowBoundary(int row, int length) {
        if (row < 0) {
            row = length;
        }
        else if(row > length) {
            row = 0;
        }

        return row;
    }
    /*
    these two methods, they're checking the boundaries for the columns & rows of our value's neighbors
    if our neighbors are out of bounds then we reassign the values to something in bounds
    --> the reflective position in the [][]
     */

    public int checkColBoundary(int col, int length) {
        if (col > length) {
            col = 0;
        }
        else if (col < 0) {
            col = length;
        }

        return col;
    }

}
