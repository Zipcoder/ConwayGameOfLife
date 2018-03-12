package com.zipcodeconway;

public class ConwayGameOfLife {
    private Integer dimension;
    private int[][] generation;
    public int neighbourRow;
    public int neighbourCol;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.generation = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][]start = new int[dimension][dimension];
        for(int row =0;row<dimension;row++){
            for(int col=0;col<dimension;col++){
                int val = (int) Math.round(Math.random());
                start[row][col]=val;
            }
        }

        return start;
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
        int[]neighbours = getNeignbours(row,col,world);
        if(world[row][col]==1 && getNumberOfLiveNeighbours(neighbours)<2){ world[row][col]=0; }
        else if(world[row][col]==1 && getNumberOfLiveNeighbours(neighbours)>3){world[row][col]=0; }
        else if(world[row][col]==1 && (getNumberOfLiveNeighbours(neighbours)==3)||(getNumberOfLiveNeighbours(neighbours)==2)){world[row][col]=1; }
        else if (world[row][col]==0&& getNumberOfLiveNeighbours(neighbours)==3){world[row][col]=1}


        return world[row][col];
    }
    public int[] getNeignbours(int row, int col,int[][]input){
        int[] neignbours = new int[8];
        for(int i=0;i<8;i++){
            checkBoundries(row,col,input);
            neignbours[i]=input[neighbourRow][neighbourCol];
        }
        return neignbours;
    }
    public int getNumberOfLiveNeighbours(int[]neighbours){
        int count=0;
        for(int i=0;i<neighbours.length;i++){
            count+=neighbours[i];
        }
        return count;
    }
    public void checkBoundries(int row, int col,int[][]input) {
        if (row - 1 < 0) {
            neighbourRow = input.length;
        } else {
            neighbourRow = row - 1;
        }
        if (row + 1 > input.length) {
            neighbourRow = 0;
        } else {
            neighbourRow = row + 1;
        }
        if (col - 1 < 0) {
            neighbourCol = input[0].length;
        } else {
            neighbourCol = col - 1;
        }
        if (col + 1 > input[0].length) {
            neighbourCol = 0;
        } else {
            neighbourCol = col + 1;
        }
    }

}
