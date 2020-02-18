/*
James Wilkinson's implimentation of Conway's Game of Life
2020 / 02 / 18
*/

package com.zipcodeconway;

public class ConwayGameOfLife
{
    private Integer[][] world;

    public ConwayGameOfLife(Integer dimension)
    {
        world = new Integer[dimension][dimension];
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix)
    {
        world = new Integer[dimension][dimension];

        for(Integer row = 0; row < dimension; row++)
        {
            for(Integer column = 0; column < dimension; column++)
            {
                world[row][column] = startmatrix[row][column];
            }
        }
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
    private int isAlive(int row, int col, int[][] world) {
        return 0;
    }
}
