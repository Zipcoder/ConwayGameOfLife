package com.zipcodeconway;

public class ConwayGameOfLife {

    private Integer dimension;
    private int[][] startmatrix;
    private SimpleWindow displayWindow;



    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.startmatrix = createRandomStart(dimension);
        this.displayWindow = new SimpleWindow(dimension);

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.dimension = dimension;
        this.startmatrix = createRandomStart(dimension);
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
        int[][] next = new int[dimension][dimension];
        for(int i = 0; i<=maxGenerations; i++) {
            this.displayWindow.display(startmatrix, i);
            for (int j = 0; j < startmatrix.length; j++) {
                for (int k = 0; k < startmatrix[j].length; k++) {
                    next[j][k] = isAlive(j, k, startmatrix);
                }
            }
            displayWindow.sleep(125);
            copyAndZeroOut(next, startmatrix);
        }
        return startmatrix;
    }



    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int i = 0; i<current.length; i++){
            for(int j = 0; j<current[i].length; j++){
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

    private int isAlive(int row, int col, int[][] world) {
        int rowTop = row - 1;
        int rowBottom = row + 1;
        int colRight = col + 1;
        int colLeft = col - 1;

        if (row == 0) rowTop = world.length - 1;
        if (row == (world.length - 1)) rowBottom = 0;
        if (col == 0) colLeft = world[row].length - 1;
        if (col == (world[row].length - 1)) colRight = 0;

        int count = world[row][colRight] + world[row][colLeft];
        count += world[rowTop][colLeft] + world[rowTop][col] + world[rowTop][colRight];
        count += world[rowBottom][colLeft] + world[rowBottom][col] + world[rowBottom][colRight];

        int alive = 0;
        if (world[row][col] == 0) {
            if (count == 3) alive = 1;
            else alive = 0;
        }
        if (world[row][col] == 1) {
            if (count == 2 || count == 3) alive = 1;
            else alive = 0;
        }
        return alive;
    }


}
