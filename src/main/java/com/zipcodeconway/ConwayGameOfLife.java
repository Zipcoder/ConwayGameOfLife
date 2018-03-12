package com.zipcodeconway;

public class ConwayGameOfLife {

    private Integer edge;
    int[][] currentGen;
    int[][] nextGen;
    private SimpleWindow ourDisplayWindow;


    public ConwayGameOfLife(Integer dimension) {
        this.ourDisplayWindow= new SimpleWindow(dimension);
        this.edge=dimension-1;
        this.nextGen = new int[dimension][dimension];
    }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.ourDisplayWindow= new SimpleWindow(dimension);
        this.edge=dimension-1;
        this.currentGen = startMatrix;
        this.nextGen = new int[dimension][dimension];

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

        for (int i = 0; i <= maxGenerations; i++){
            this.ourDisplayWindow.display(currentGen, i);
            this.ourDisplayWindow.sleep(125);
            copyAndZeroOut(nextGen,currentGen);
        }

        return currentGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int[][] next, int[][] current) {
        for (int i = 0; i < next.length ; i++){
            for (int j = 0; j <next.length; j++) {
                next[i][j] = isAlive(i,j,current);
            }
        }

        for (int i = 0; i < next.length ; i++){
            for (int j = 0; j <next.length; j++) {
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
    protected int isAlive(int row, int col, int[][] world) {
        //all we're returning is a binary. 1 = alive for next gen.
        Integer aliveForNextGen;
        Integer numOfLiveCells = 0;

        for (int cellWereLookingAthorizontally = row -1; cellWereLookingAthorizontally <=row +1; cellWereLookingAthorizontally++){
            int rowCheck = cellWereLookingAthorizontally;
            if (cellWereLookingAthorizontally <0){
                rowCheck=edge;
            } else if (cellWereLookingAthorizontally > edge) {
                rowCheck = 0;
            }

            for (int cellWereLookingAtVertically = col -1; cellWereLookingAtVertically <= col +1; cellWereLookingAtVertically++){
                int colCheck = cellWereLookingAtVertically;
                if (cellWereLookingAtVertically <0 ){
                    colCheck = edge;
                } else if (cellWereLookingAtVertically > edge){
                    colCheck = 0;
                }

                if (world[rowCheck][colCheck]==1) numOfLiveCells++;
            }
        }


        if (numOfLiveCells ==3) {
            aliveForNextGen = 1;
        } else if (numOfLiveCells ==4){
            aliveForNextGen= world[row][col];
        } else {
            aliveForNextGen = 0;
        }

        return aliveForNextGen;
    }

}
