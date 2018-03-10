package com.zipcodeconway;


import java.util.Random;

public class ConwayGameOfLife {
    private SimpleWindow displayWindow;
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private int generations;
    private int edge;


    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
        this.edge = dimension-1;
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = startmatrix;
        this.nextGeneration = new int[dimension][dimension];
        this.edge = dimension-1;


    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(100);
        int[][] endingWorld = sim.simulate(1000);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        Random liveOrDead = new Random();
        int[][] randomStart = new int[dimension][dimension];

        for (int i = 0; i <dimension; i++) {
            for (int j = 0; j <dimension; j++) {
            randomStart[i][j] = liveOrDead.nextInt(101)+0 >65 ? 0 : 1;
            }
        }



        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        for(int i = 0; i<=maxGenerations; i++) {
            this.displayWindow.display(currentGeneration, generations);
            this.displayWindow.sleep(75);
            this.copyAndZeroOut();
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    private void copyAndZeroOut() {

        for(int i = 0; i<= edge; i++){
            for(int j = 0; j<= edge; j++){
                nextGeneration[i][j] = this.isAlive(i,j,currentGeneration);
            }
        }
        for(int i = 0; i<= edge; i++){
            for(int j = 0; j<= edge; j++){
                currentGeneration[i][j] = nextGeneration[i][j];
                nextGeneration[i][j] = 0;
            }
        }
        generations++;
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
        int liveCells = 0;

        for(int i = row-1; i<=row+1; i++){
            int iLooker = i;
            if(i<0) iLooker = edge;
            else if (i>edge) iLooker = 0;

            for(int j = col-1; j<=col +1; j++){
                int jLooker = j;
                if(j<0) jLooker = edge;
                else if (j>edge) jLooker = 0;

                if(world[iLooker][jLooker] == 1) liveCells++;
            }
        }

        if(liveCells == 3) return 1;
        else if(liveCells == 4) return world[row][col];
        else return 0;
    }
}
