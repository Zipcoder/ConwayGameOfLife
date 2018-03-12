package com.zipcodeconway;

import java.util.Random;
import java.util.Scanner;

public class ConwayGameOfLife {

    private SimpleWindow window;
    private int[][] currentGeneration;
    private int[][] nextGeneration;

    public ConwayGameOfLife(Integer dimension) {
        this.window = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
        this.nextGeneration = new int[dimension][dimension];
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.window = new SimpleWindow(dimension);
        this.currentGeneration = startMatrix;
        this.nextGeneration = new int[dimension][dimension];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many generations?");
        int generations = input.nextInt();
        System.out.println("How many pixels big will the simulation be?");
        int simDimensions = input.nextInt();
        System.out.println("Running simulation...");
        ConwayGameOfLife sim = new ConwayGameOfLife(simDimensions);
        sim.simulate(generations);
        System.out.println("Simulation complete");
    }

    private int[][] createRandomStart(Integer dimension) {
        int[][] randoStarto = new int[dimension][dimension];
        for (int i = 0; i < randoStarto.length; i++) {
            for (int j = 0; j < randoStarto[i].length; j++) {
                randoStarto[i][j] = new Random().nextInt(2);
            }
        }
        return randoStarto;
    }

    public int[][] simulate(Integer maxGenerations) {
        int count = 0;
        while (count <= maxGenerations) {
            this.window.display(currentGeneration, maxGenerations);
            for (int i = 0; i < currentGeneration.length; i++) {
                for (int j = 0; j < currentGeneration[i].length; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }
            copyAndZeroOut(nextGeneration, currentGeneration);
            this.window.sleep(125);
            count++;
        }
        return currentGeneration;
    }

    private void copyAndZeroOut(int [][] next, int[][] current) {
        for (int i = 0; i < current.length; i++){
            for (int j = 0; j < current[i].length; j++){
                current[i][j] = next[i][j];
                next[i][j] = 0;
            }
        }
    }

    private int isAlive(int row, int col, int[][] world) {
        int aliveCounter = 0;
        int leftOne = row - 1;
        int rightOne = row + 1;
        int downOne = col - 1;
        int upOne = col + 1;
        if (leftOne == -1) {
            leftOne = world.length - 1;
        }
        if (rightOne == world.length) {
            rightOne = 0;
        }
        if (downOne == -1) {
            downOne = world[row].length - 1;
        }
        if (upOne == world[row].length) {
            upOne = 0;
        }
        if (world[leftOne][downOne] == 1) {aliveCounter++;}
        if (world[leftOne][col] == 1) {aliveCounter++;}
        if (world[leftOne][upOne] == 1) {aliveCounter++;}
        if (world[row][downOne] == 1) {aliveCounter++;}
        if (world[row][upOne] == 1) {aliveCounter++;}
        if (world[rightOne][downOne] == 1) {aliveCounter++;}
        if (world[rightOne][col] == 1) {aliveCounter++;}
        if (world[rightOne][upOne] == 1) {aliveCounter++;}
        if (aliveCounter < 2){
            return 0;
        } else if (aliveCounter > 3){
            return 0;
        } else if (aliveCounter == 3){
            return 1;
        } else {
            return world[row][col];
        }
    }

}
