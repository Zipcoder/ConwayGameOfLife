package com.zipcodeconway;


public class ConwayGameOfLife {

    private int[][] next;
    private int[][] currentGen;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        next = new int[dimension][dimension];
        currentGen = createRandomStart(dimension);
        this.displayWindow = new SimpleWindow(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        currentGen = startmatrix;
        next = new int [dimension][dimension];
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
        //creating random world[][]
        int[][] newWorld = new int[dimension][dimension];
        for(int row = 0; row < newWorld.length; row++){
            for(int col = 0; col < newWorld[row].length; col++){
                newWorld[row][col] = (int)(Math.random() * 2);
            }
        }

        return newWorld;
    }

    public int[][] simulate(Integer maxGenerations) {
        //updating the next from the current generation
        //setting alive neighbors from isAlive method into next generation
        int counter = 0;
        while(counter <= maxGenerations){
            displayWindow.display(currentGen, counter);
            for(int row = 0; row < currentGen.length; row++){
                for(int col = 0; col < currentGen[row].length; col++){
                    next[row][col] = isAlive(row, col, currentGen);
                }
            }
            copyAndZeroOut(next, currentGen);
            displayWindow.sleep(125);
            counter++;
        }
        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int row = 0; row < next.length; row++){
            for(int col = 0; col < current.length; col++){
                current[row][col] = next[row][col];
                next[row][col] = 0;
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
        //declaring directions to move and check for neighbors
        int north = col - 1;
        int south = col + 1;
        int east= row + 1;
        int west = row - 1;
        int countNeighbor = 0;

        //boundaries that loop move [][]
        if(north < 0) north = world.length -1;
        if(west < 0) west = world.length - 1;
        if(south == world.length) south = 0;
        if(east == world.length) east = 0;

        //checking how many neighbors are alive in each direction
        if(world[east][north] == 1) countNeighbor++;
        if(world[east][col] == 1) countNeighbor++;
        if(world[east][south] == 1) countNeighbor++;

        if(world[west][north] == 1) countNeighbor++;
        if(world[west][col] == 1) countNeighbor++;
        if(world[west][south] == 1) countNeighbor++;

        if(world[row][north] == 1) countNeighbor++;
        if(world[row][south] == 1) countNeighbor++;

        //determining whether the neighbor is alive or dead and returning that world
        if(countNeighbor < 2 || countNeighbor > 3) return 0;
        else if(countNeighbor == 3) return 1;
        else return world[row][col];
    }
}
/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/