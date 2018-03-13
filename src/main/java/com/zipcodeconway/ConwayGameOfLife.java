package com.zipcodeconway;



public class ConwayGameOfLife {

    private SimpleWindow displayWindow;
    private int [][] currentGen;
    private int [][] nextGen;

    public ConwayGameOfLife(Integer dimension)
    {
        this.displayWindow = new SimpleWindow(dimension);
        currentGen = createRandomStart(dimension);
        nextGen = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix)
    {
        this.displayWindow = new SimpleWindow(dimension);
        currentGen = startmatrix;
        nextGen = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    public int[][] simulate(Integer maxGenerations)
    {
        //Incrementing the generations
        int generations = 0;

        while (generations <= maxGenerations)
        {

            displayWindow.display(currentGen, generations);

            for(int row = 0; row < currentGen.length; row++)
            {
                for (int column = 0; column < currentGen[row].length; column++)
                {
                    nextGen[row][column] = isAlive(row, column, currentGen);
                }
            }

            copyAndZeroOut(nextGen, currentGen);
            displayWindow.sleep(125);
            generations++;
        }

        return currentGen;
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {

        //To start the game you have to create a multidimensional array

        int[][] randomArray = new int [dimension][dimension];

        for(int row = 0; row < randomArray.length; row++)
        {
            for(int column = 0; column < randomArray[row].length; column++)
            {
                randomArray[row][column] = (int)(Math.random()*2);
            }
        }

        return randomArray;
    }



    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current)
    {
        for (int row = 0; row < current.length; row++)
        {
            for (int column = 0; column < current[row].length; column++)
            {
                current[row][column] = next[row][column];

                next[row][column] = 0;
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
    private int isAlive(int row, int col, int[][] world)
    {
        //instance variables that specify the relation to the cell
        int countNeighbors = 0;
        int north = col - 1;
        int south = col + 1;
        int east = row + 1;
        int west = row - 1;

        //boundaries take a loop
        if (north < 0)
        {
            north = world[row].length - 1;
        }
        if (south == world[row].length)
        {
            south = 0;
        }
        if (east == world[col].length)
        {
            east = 0;
        }
        if (west < 0)
        {
            west = world[col].length - 1;

        }

        if(world[east][north]== 1) countNeighbors++;
        if(world[east][south]== 1) countNeighbors++;
        if(world[east][col]== 1) countNeighbors++;

        if(world[west][north]== 1) countNeighbors++;
        if(world[west][south]== 1) countNeighbors++;
        if(world[west][col] == 1) countNeighbors++;

        if(world[row][north]== 1) countNeighbors++;
        if(world[row][south] == 1) countNeighbors++;

        //Dies
        if (countNeighbors < 2)
        {
            return 0;
        }
        else if (countNeighbors > 3)
        {
            return 0;
        }

        //Dead cell comes to life
        else if (countNeighbors == 3)
        {
           return 1;
        }

        else return world[row][col]; // unchanged

    }
}
