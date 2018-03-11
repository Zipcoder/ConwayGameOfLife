package com.zipcodeconway;

public class ConwayGameOfLife {
    private int size;
    private SimpleWindow window;
    private int[][] board;

    public int getSize() {
        return size;
    }

    public ConwayGameOfLife(){
        this(50);
    }

    public ConwayGameOfLife(Integer dimension) {
        this(dimension, ConwayGameOfLife.createRandomStart(dimension));
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.window = new SimpleWindow(dimension);
        this.board = startMatrix;
        this.size = dimension;
        window.display(startMatrix, 1);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    public static int[][] createRandomStart(Integer dimension) {
        int[][] rando = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                rando[i][j] = (int)Math.round(Math.random());
            }
        }
        return rando;
    }

    public int[][] simulate(Integer maxGenerations) {
        int currentGen = 1;
        int[][] currentBoard = this.board;
        while (currentGen < maxGenerations){
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    currentBoard[i][j] = isAlive(i, j, currentBoard);
                }
            }
        }
        return currentBoard;
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
    public int isAlive(int row, int col, int[][] world) {
        int neighborCount = countNeighbors(row, col, world);
        if (world[row][col] == 1) {
            if (neighborCount < 2) return 0;
            if (neighborCount == 2 || neighborCount == 3) return 1;
            if (neighborCount > 3) return 0;
        } else {
            if (neighborCount == 3) return 1;
        }
        return 0;
    }

    private int countNeighbors(int row, int col, int[][] world) {
        int neighborCount = 0;
        for (int i = row-1; i <= row+1; i++){
            for (int j = col -1; j <= col + 1; j++){
                if (world[i][j] == 1 && !(i == row && j == col)) {
                    neighborCount++;
                } else {
                }
            }
        }
        return neighborCount;
    }

    public int[][] neighborCoordGenerator(int row, int col){
        int[][] subworld = new int[2][3];
        for (int i = row-1; i <= row+1; i++){

        }
        return subworld;
    }

    public int loopCheck(int coord){
        if (coord < 0) return this.size - coord;
        if (coord > this.size) return coord - this.size;
        return coord;
    }

}
