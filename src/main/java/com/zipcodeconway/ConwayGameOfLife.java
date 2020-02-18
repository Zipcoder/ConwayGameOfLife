/*
James Wilkinson's implimentation of Conway's Game of Life
2020 / 02 / 18
*/

package com.zipcodeconway;

public class ConwayGameOfLife
{
    private int[][] currentWorld;
    private int[][] futureWorld;
    Integer worldSize;

    public ConwayGameOfLife(Integer dimension)
    {
        currentWorld = new int[dimension][dimension];
        futureWorld = new int[dimension][dimension];
        worldSize = dimension;
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix)
    {
        currentWorld = new int[dimension][dimension];

        for(Integer row = 0; row < dimension - 1; row++)
        {
            for(Integer column = 0; column < dimension - 1; column++)
            {
                currentWorld[row][column] = startmatrix[row][column];
            }
        }
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingcurrentWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension)
    {
        int[][] startingWorld = new int[50][50];

        for(Integer row = 0; row < dimension - 1; row++)
        {
            for(Integer column = 0; column < dimension - 1; column++)
            {
          //      Integer cellStartState = Math.round(Math.random());
          //      startingWorld[row][column] = cellStartState;
            }
        }

        return startingWorld;
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
    private int isAlive(int row, int col, int[][] currentWorld)
    {
        Integer liveNeighborCount = 0;
        for(Integer i = -1; i < 2; i++)
        {
            for(Integer k = -1; k < 2; k++)
            {
                Integer thisRow = ((row + i + worldSize) % worldSize);
                Integer thisCol = ((col + k + worldSize) % worldSize);
                if(!(i == 0 && k == 0))
                {
                    if(currentWorld[thisRow][thisCol] == 1)
                    {
                        liveNeighborCount++;
                    }
                }
            }
        }

        if(liveNeighborCount < 2 && currentWorld[row][col] == 1)
        {
            return 0;
        } else if(liveNeighborCount > 3 && currentWorld[row][col] == 1)
        {
            return 0;
        } else if(liveNeighborCount == 3 && currentWorld[row][col] == 0)
        {
            return 1;
        } else
        {
            return currentWorld[row][col];
        }
    }
}
