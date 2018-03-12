package com.zipcodeconway;

public class ConwayGameOfLife {
    private int[][] currentGen;
    private int[][] nextGen;
    private SimpleWindow displayWindow;


    public ConwayGameOfLife(Integer dimension) {
        this.currentGen = createRandomStart(dimension);
        nextGen = new int[dimension][dimension];
        this.displayWindow = new SimpleWindow(dimension);

     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        currentGen = startmatrix;
        nextGen = new int[dimension][dimension];
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
        // make new array and define with dimension
        int[][] randomArray = new int[dimension][dimension];
        for(int row = 0; row < dimension; row++) {
            for(int col = 0; col < dimension; col++) {
                randomArray[row][col] = (int) (Math.random() * 2);
            }
        }
        return  randomArray;
    }

    public int[][] simulate(Integer maxGenerations) {
       int generations = 0;

       while(generations <= maxGenerations) {
           this.displayWindow.display(currentGen, generations);

           for(int row = 0; row < currentGen.length; row++ ) {
               for(int col = 0; col < currentGen[row].length; col++) {
                   nextGen[row][col] = isAlive(row, col, currentGen);
               }
           }
           copyAndZeroOut(nextGen, currentGen);
           this.displayWindow.sleep(125);
           generations++;
       }
       return currentGen;

    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int row = 0; row < current.length; row++) {
            for(int col = 0; col < current.length; col++) {
                // set next = current
                current[row][col] = next[row][col];
                // zero out next
                next[row][col] = 0;

            }
        }


    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:

    private int isAlive(int row, int col, int[][] world) {
        int numNeighbors = 0;
        int north = col -1;
        int south = col + 1;
        int east = row + 1;
        int west = row -1;

        if (north < 0) {
            north = world[row].length -1;
        }

        if (south == world[row].length) {
            south = 0;
        }

        if (east == world[col].length) {
            east = 0;
        }

        if (west < 0) {
            west = world[col].length - 1;
        }


        if(world[east][north] == 1) numNeighbors++;
        if(world[east][col] == 1) numNeighbors++;
        if(world[east][south] == 1) numNeighbors++;

        if(world[west][north] ==1) numNeighbors++;
        if(world[west][south] == 1) numNeighbors++;
        if(world[west][col] == 1) numNeighbors++;

        if(world[row][north] ==1) numNeighbors++;
        if(world[row][south] ==1) numNeighbors++;

        /*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
        if(numNeighbors < 2 || numNeighbors > 3 ) {
            return 0;
        }else if(numNeighbors == 3){
            return 1;
        } else {
            // return it unchanged
            return world[row][col];
        }

    }
}
