package com.zipcodeconway;

public class ConwayGameOfLife {
    private int[][] currentGeneration;
    private int[][] nextGeneration;
    private SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] currentGeneration) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = currentGeneration;
        this.nextGeneration = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomMatrix = new int[dimension][dimension];
        for (int i = 0; i < randomMatrix.length; i++) {
            for (int j = 0; j < randomMatrix[i].length; j++) {
                randomMatrix[i][j] = (int) Math.round(Math.random());
            }
        }
        return randomMatrix;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generation = 0;
        while (generation <= maxGenerations) {
            this.displayWindow.display(currentGeneration, generation);
            for (int i = 0; i < currentGeneration.length; i++) {
                for (int j = 0; j < currentGeneration[i].length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            generation++;
            this.displayWindow.sleep(125);
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {
                current[i][j] = next[i][j];
                next[i][j] = 0;
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
        int aliveCount = 0;
        int up = (row == 0 ? row : row - 1);
        int down = (row == world.length - 1 ? row : row + 1);
        int left = (col == 0 ? col : col - 1);
        int right = (col == world[row].length - 1 ? col : col + 1);
        if (world[up][col] == 1) {
            if (up != row) {
                aliveCount++;
            }
        }
        if (world[up][left] == 1) {
            if (up != row && left != col) {
                aliveCount++;
            }
        }
        if (world[up][right] == 1) {
            if (up != row && right != col) {
                aliveCount++;
            }
        }
        if (world[row][left] == 1) {
            if (left != col) {
                aliveCount++;
            }
        }
        if (world[row][right] == 1) {
            if (right != col) {
                aliveCount++;
            }
        }
        if (world[down][col] == 1) {
            if (down != row) {
                aliveCount++;
            }
        }
        if (world[down][left] == 1) {
            if (down != row && left != col) {
                aliveCount++;
            }
        }
        if (world[down][right] == 1) {
            if (down != row && right != col) {
                aliveCount++;
            }
        }
        if (aliveCount < 2 || aliveCount > 3) {
            return 0;
        } else if (aliveCount == 3) {
            return 1;
        } else {
            return world[row][col];
        }
    }
}
