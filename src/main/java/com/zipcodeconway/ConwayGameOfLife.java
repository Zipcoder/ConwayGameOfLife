package com.zipcodeconway;

public class ConwayGameOfLife {
    SimpleWindow displayWindow;
    int[][] currentGeneration;
    int[][] nextGeneration;
    int generations;
    int edge;


    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.edge = dimension-1;
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = startmatrix;
        this.edge = dimension-1;

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
        this.displayWindow.display(currentGeneration,generations);
        this.displayWindow.sleep(125);
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
        int liveCells=0;
        for(int i = row-1; i<row+1; i++){
            int iLooker;
            if(i<0) iLooker = edge;
            else if (i>edge) iLooker =0;
            else iLooker = i;

            for(int j = col-1; j<col +1; j++){
                int jLooker;
                if(j<0) jLooker = edge;
                else if (j>edge) jLooker =0;
                else jLooker = j;

                if(world[iLooker][jLooker] == 1) liveCells++;
            }
        }

        if(liveCells == 3) return 1;
        else if(liveCells == 4) return world[row][col];
        else return 0;

    }
}
