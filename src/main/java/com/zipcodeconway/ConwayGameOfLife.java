package com.zipcodeconway;

public class ConwayGameOfLife {
    int[][] currentGeneration;
    int[][] nextGeneration;

    public SimpleWindow displayWindow;

    public ConwayGameOfLife(Integer dimension) {
        currentGeneration = createRandomStart(dimension);
        nextGeneration = new int[dimension][dimension];
        displayWindow = new SimpleWindow(dimension);

    }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        currentGeneration = startmatrix;
        nextGeneration = new int[dimension][dimension];
        displayWindow = new SimpleWindow(dimension);


    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomStart = new int[dimension][dimension];
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                randomStart[row][col] = (int) (Math.random() * 2);
            }
        }
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        int generations = 0;
        while (generations <= maxGenerations) {
            this.displayWindow.display(currentGeneration, generations);

            for (int row = 0; row < currentGeneration.length; row++) {
                for (int col = 0; col < currentGeneration.length; col++) {
                    nextGeneration[row][col] = isAlive(row, col, currentGeneration);
                }
            }

            copyAndZeroOut(nextGeneration, currentGeneration);
            this.displayWindow.sleep(125);
            generations++;
        }

        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int row = 0; row < current.length; row++) {
            for (int col = 0; col < current.length; col++) {
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
         int up = row -1;
         int down = row + 1;
         int right = col + 1;
         int left = col -1;

        int amountOfNeighborsAlive = 0;
//        Cell cell = new Cell(row, col);
        int lifeDeathValue = world[row][col];

        if (up < 0){
            up = world[row].length -1;
        }
        if (down == world[row].length){
            down = 0;
        }
        if (right == world[col].length){
            right = 0;
        }
        if (left < 0){
            left = world[col].length -1;
        }


        if (world[down][right] > 0) amountOfNeighborsAlive++;
        if (world[down][left] > 0) amountOfNeighborsAlive++;
        if (world[up][left] > 0) amountOfNeighborsAlive++;
        if (world[up][right] > 0) amountOfNeighborsAlive++;
        if (world[row][left] > 0) amountOfNeighborsAlive++;
        if (world[row][right] > 0) amountOfNeighborsAlive++;
        if (world[down][col] > 0) amountOfNeighborsAlive++;
        if (world[up][col] > 0) amountOfNeighborsAlive++;

        if (amountOfNeighborsAlive> 3 || amountOfNeighborsAlive < 2) {
            lifeDeathValue =  0;
        } else if (lifeDeathValue == 0 && amountOfNeighborsAlive == 3) {
            lifeDeathValue = 1;
        }
        return lifeDeathValue; // only returns a one a zero
    }

}


