package com.zipcodeconway;

public class ConwayGameOfLife {
    private SimpleWindow displayWindow;
    private int[][] currentGen;
    private int[][] nextGen;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGen = createRandomStart(dimension);
        this.nextGen = new int[dimension][dimension];

    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGen = startmatrix;
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
        int[][] newArray = new int[dimension][dimension];
        for(int row = 0; row < newArray.length; row++){
            for(int col = 0; col < newArray.length; col++){
                //this is taking our rows and cols and generating a random int into them
                newArray[row][col]= (int)Math.round(Math.random());
            }
        }
        //its returning the whole array not just one cell
        return newArray;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generations = 0;
        while(generations <= maxGenerations){
            this.displayWindow.display(currentGen, generations);
            for(int row = 0; row < currentGen.length; row++){
                for(int col = 0; col < currentGen.length; col++) {
                    nextGen[row][col]=isAlive(row, col, currentGen);
                }
            }
            copyAndZeroOut(nextGen, currentGen);
            this.displayWindow.sleep(125);
            //killing the while loop
            generations++;
        }
        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int row = 0; row < next.length; row++){
            for(int col = 0; col < current.length; col++) {
                current[row][col]=next[row][col];
                next[row][col]=0;
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
        int north = col -1;
        int south = col + 1;
        int east = row + 1;
        int west = row -1;

        int countNeighbor = 0;


        //these are the boundaries that loop around
        if(north<0)
            north = world.length -1;
        if(west<0)
            west = world.length-1;
        //katrice, draw it out on some paper...this spot stands for the top position in the col
        if(south==world.length)
            south = 0;
        if(east==world.length)
            east = 0;

        //checking how many neighbors are alive

        if(world[east][north]==1) countNeighbor++;
        if(world[east][south]==1) countNeighbor++;
        if(world[east][col]==1) countNeighbor++;

        if(world[west][north]==1) countNeighbor++;
        if(world[west][south]==1) countNeighbor++;
        if(world[west][col]==1) countNeighbor++;

        if(world[row][north]==1) countNeighbor++;
        if(world[row][south]==1) countNeighbor++;

        /*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
        if(countNeighbor<2 || countNeighbor > 3) {
            return 0;
        }else if(countNeighbor == 3){
            return 1;
        } else
            return world[row][col];

    }
}
