package com.zipcodeconway;

public class ConwayGameOfLife {

    private int generations;
    private int[][] currentGeneration;
    private int[][] next;
    private SimpleWindow displayWindow;

//constructor to create a random start
    public ConwayGameOfLife(Integer dimension) {
        currentGeneration = createRandomStart(dimension);
        this.displayWindow = new SimpleWindow(dimension);
        next = new int [dimension][dimension];

    }
//constructor to start with a set board
    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        currentGeneration = startmatrix;
        this.displayWindow = new SimpleWindow(dimension);
        next = new int [dimension][dimension];


    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);

    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        currentGeneration = new int[dimension][dimension];
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                currentGeneration[x][y] =  (int)(Math.random() * 2);
            }
        }
        return currentGeneration;
    }

    public int[][] simulate(Integer maxGenerations) {
        for (int i = 0; i <= maxGenerations; i++) {
            this.displayWindow.display(currentGeneration, generations);
            for (int a = 0; a < currentGeneration.length ; a++) {
                for (int b = 0; b < currentGeneration[a].length; b++) {
                 next[a][b] = isAlive(a, b, currentGeneration);
                }

            }
            copyAndZeroOut(next, currentGeneration);
            this.displayWindow.sleep(125);
            generations++;

        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {

        for (int i = 0; i <next.length ; i++) {
            for (int j = 0; j < next.length ; j++) {
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
        int north = col -1;
        int south = col +1;
        int east = row +1;
        int west = row -1;

        if(north < 0){
            north = world[row].length - 1;
        }
        if(south == world[row].length){
            south = 0;
        }
        if(west < 0){
            west = world[col].length-1;
        }
        if (east == world[col].length) {
            east = 0;
        }

        int aliveNeighbors = world[row][north] + world[east][north] + world[west][north]
                + world[row][south] + world[east][south] + world[west][south]
                + world[east][col] + world[west][col];

        if (aliveNeighbors == 3){
            return 1;
        }
        if (aliveNeighbors < 2){
            return 0;
        }
        if (aliveNeighbors > 3){
            return 0;
        }
        return world[row][col];


    }



//
//        int aliveNeighbors = 0;
//        for (int i = -1; i <= 1; i++) {
//            for (int j = -1; j <= 1; j++) {
////                aliveNeighbors += world
////                        [(i + row) % world[row].length]
////                        [(j + col) % world[col].length];
//            }
//        }
//                aliveNeighbors -= world[row][col];
//                //Rules of Life
//                //cell is lonely and dies
//
//                if ((world[row][col] == 1) && (aliveNeighbors < 2)) {
//                    next[row][col] = 0;
//                }
//                //cell is overcrowded and dies
//                else if ((world[row][col] == 1) && (aliveNeighbors > 3)) {
//                    next[row][col] = 0;
//                }
//                //dead cell comes to life
//                else if ((world[row][col] == 0) && (aliveNeighbors == 3)) {
//                    next[row][col] = 1;
//                }
//                //cell remains unchanged
//                else {
//                    next[row][col] = world[row][col];
//                }
//
//        return next[row][col];
//
//    }
//
//



//        next = new int[row][col];
//        for (int x = 1; x < row -1  ; x++) {
//            for (int y = 1; y < col -1  ; y++) {
//                int aliveNeighbors = 0;
//                for (int j = -1; j <= 1; j++) {
//                    for (int k = -1; k <= 1; k++) {
//
//                        aliveNeighbors += world[(x + j+row) % row][(y + k + col) %col];
//
//                        //cell needs to be subtracted from aliveNeighbors because it was counted before
//                        aliveNeighbors -= world[x][y];
//                        //Rules of Life
//                        //cell is lonely and dies
//                        if ((world[x][y] == 1) && (aliveNeighbors < 2)) {
//                            next[x][y] = 0;
//                        }
//                        //cell is overcrowded and dies
//                        else if ((world[x][y] == 1) && (aliveNeighbors > 3)) {
//                            next[x][y] = 0;
//                        }
//                        //dead cell comes to life
//                        else if ((world[x][y] == 0) && (aliveNeighbors == 3)) {
//                            next[x][y] = 1;
//                        }
//                        //cell remains unchanged
//                        else {
//                            next[x][y] = world[x][y];
//                        }
//                    }
//                }
//
//            }
//            return world[x][y];


}
