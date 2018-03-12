package com.zipcodeconway;

import java.util.Arrays;
import java.util.Random;

public class ConwayGameOfLife {

    private SimpleWindow simpleWindow;

    private Integer dimension;
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private int[][] nextArrayAfter;
    private int[][] currentArrayAfter;

    public ConwayGameOfLife(Integer dimension) {
        this.simpleWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.dimension = dimension;
        this.currentGeneration = startMatrix;
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomArray = new int[dimension][dimension];

        Random random = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            for (int j = 0; j < randomArray[i].length; j++) {
                int zeroOrOne = random.nextInt(2);
                randomArray[i][j] = zeroOrOne;
            }
        }
        return randomArray;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generation = 0;
        while (generation <= maxGenerations) {
            this.simpleWindow.display(currentGeneration, generation);
            for (int i = 0; i < currentGeneration.length; i++) {
                for (int j = 0; j < currentGeneration.length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            this.simpleWindow.sleep(125);
            generation++;
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        this.nextArrayAfter = new int[next.length][next.length];
        this.currentArrayAfter = new int[next.length][next.length];

        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {
                current[i][j] = next[i][j];
                currentArrayAfter[i][j] = next[i][j];
            }
        }

        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next[i].length; j++) {
                next[i][j] = 0;
                nextArrayAfter[i][j] = 0;
            }
        }
    }

    public int[][] getNextArrayAfter() {
        return nextArrayAfter;
    }

    public int[][] getCurrentArrayAfter() {
        return currentArrayAfter;
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
        int living = 0;

        int west = row - 1;
        int east = row + 1;
        int north = col - 1;
        int south = col + 1;

        if(west == -1) {
            west = world.length - 1;
        }
        if(east == world.length){
            east = 0;
        }
        if(north == -1) {
            north = world[row].length - 1;
        }
        if(south == world[row].length) {
            south = 0;
        }

        if(world[west][north] == 1) {
            living++;
        }
        if(world[west][col] == 1) {
            living++;
        }
        if(world[west][south] == 1) {
            living++;
        }
        if(world[row][north] == 1) {
            living++;
        }
        if(world[row][south] == 1) {
            living++;
        }
        if(world[east][north] == 1) {
            living++;
        }
        if(world[east][col] == 1) {
            living++;
        }
        if(world[east][south] == 1) {
            living++;
        }

        if(living < 2) {
            return 0;
        } else if (living > 3) {
            return 0;
        } else if (living == 3) {
            return 1;
        } else {
            return world[row][col];
        }
    }


    public String rowsToString(int[][] arrayToPrint) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayToPrint.length; i++) {
            sb.append(Arrays.toString(arrayToPrint[i]) + "\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(500);

    }

}
