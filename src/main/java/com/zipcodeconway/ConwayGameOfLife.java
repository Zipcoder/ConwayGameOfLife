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
    SimpleWindow gameDisplay;

    public ConwayGameOfLife(Integer dimension)
    {
        currentWorld = new int[dimension][dimension];
        futureWorld = new int[dimension][dimension];
        currentWorld = createRandomStart(dimension);
        worldSize = dimension;
        gameDisplay = new SimpleWindow(dimension);
    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix)
    {
        currentWorld = new int[dimension][dimension];
        futureWorld = new int[dimension][dimension];
        currentWorld = startmatrix;
        worldSize = dimension;
        gameDisplay = new SimpleWindow(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(100);
        int[][] endingWorld = sim.simulate(5000);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension)
    {
        int[][] startingWorld = new int[dimension][dimension];

        for(Integer row = 0; row < dimension - 1; row++)
        {
            for(Integer column = 0; column < dimension - 1; column++)
            {
                int cellStartState;
                if(Math.random() > 0.5)
                {
                    cellStartState = 1;
                }
                else
                {
                    cellStartState = 0;
                }

                startingWorld[row][column] = cellStartState;
            }
        }

        return startingWorld;
    }

    public int[][] simulate(Integer maxGenerations)
    {
        for(int i = 0; i <= maxGenerations; i++)
        {
            for(int row = 0; row < worldSize; row++)
            {
                for(int col = 0; col < worldSize; col++)
                {
                    futureWorld[row][col] = isAlive(row, col, currentWorld);
                }
            }
            gameDisplay.display(currentWorld, i + 1);
            gameDisplay.sleep(15);
            copyAndZeroOut(futureWorld, currentWorld);
        }

        return currentWorld;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int row = 0; row < worldSize; row++)
        {
            for(int col = 0; col < worldSize; col++)
            {
                current[row][col] = next[row][col];
                next[row][col] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
    public int isAlive(int row, int col, int[][] currentState)
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
                    if(currentState[thisRow][thisCol] == 1)
                    {
                        liveNeighborCount++;
                    }
                }
            }
        }
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
        if(currentState[row][col] == 1 && liveNeighborCount < 2)
        {
            return 0;
        }
        else if(currentState[row][col] == 1 && liveNeighborCount > 3)
        {
            return 0;
        }
        else if(currentState[row][col] == 1 && ((liveNeighborCount == 2) || (liveNeighborCount == 3)))
        {
            return 1;
        }
        else if(currentState[row][col] == 0 && liveNeighborCount == 3)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
