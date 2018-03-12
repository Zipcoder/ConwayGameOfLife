package com.zipcodeconway;

public class ConwayGameOfLife {
    //private SimpleWindow displayWindow;
    private Integer edge;
    int[][] currentGen;
    int[][] nextGen;


    public ConwayGameOfLife(Integer dimension) {
        //this.displayWindow = new SimpleWindow(dimension);
        this.edge = dimension -1;
        this.nextGen = new int [dimension][dimension;
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        //this.displayWindow = new SimpleWindow(dimension);
        this.edge = dimension -1;
        this.currentGen = startmatrix;
        this.nextGen = new int [dimension][dimension;

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
    protected int isAlive(int row, int col, int[][] world) {
    //is alive in next generation//

        int nextGenLives;
        int counter = 0;

        for(int i = row - 1; i <= row + 1; i++) {
            int iSeeker = i;
            if(i < 0){
                iSeeker = edge;
            } else if(i > edge){
                iSeeker = 0;
            }

            for(int j = col - 1; j <= col + 1; j++){
                int jSeeker = j;
                if(j < 0){
                    jSeeker = edge;
                } else if(j > edge){
                    jSeeker = 0;
                }
                if(world[iSeeker][jSeeker] == 1) counter++;
            }
        }

        if (counter == 3){
            nextGenLives = 1;
        } else if (counter == 4){
            nextGenLives = world[row][col];
        } else {
            nextGenLives = 0;
        }

        return nextGenLives;
    }
}
