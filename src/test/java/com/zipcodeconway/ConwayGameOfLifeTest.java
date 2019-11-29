package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ConwayGameOfLifeTest {

    @Test
    public void runTest1() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(9);

        System.out.println("");
        sim.printMatrix(results);
        System.out.println("");
        sim.printMatrix(expected);
        System.out.println("");

        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest2() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(10);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void createRandom() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] array = sim.createRandomStart(20);

        sim.printMatrix(array);

        int count = Arrays.stream(array).flatMapToInt(row -> Arrays.stream(row)).sum();
        Assert.assertTrue(count <= 400);
        Arrays.stream(array).flatMapToInt(row -> Arrays.stream(row)).forEach(x-> Assert.assertTrue(x == 0 || x == 1));
    }

    @Test
    public void copyAndZeroTest() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] array = sim.createRandomStart(20);

        int[][] current = sim.createRandomStart(20);
        int[][] next = new int [20][20];
        for (int i = 0; i < next[0].length; i++) {
            next[i] = Arrays.copyOf(array[i],array[i].length);
        }
        sim.copyAndZeroOut(next,current);

        sim.printMatrix(array);
        System.out.println("");
        sim.printMatrix(current);
        System.out.println("");
        sim.printMatrix(next);

        Arrays.stream(next).flatMapToInt(row -> Arrays.stream(row)).forEach(x-> Assert.assertTrue(x == 0));
        assertTrue(java.util.Arrays.deepEquals(array,current));
    }

    @Test
    public void isAliveTest() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] world = {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 0}};

        int[][] results = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                results[i][j] = sim.isAlive(i,j,world);
            }
        }
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 1, 0, 1, 0}};

        System.out.println("");
        sim.printMatrix(results);
        System.out.println("");
        sim.printMatrix(expected);
        System.out.println("");

        assertTrue(java.util.Arrays.deepEquals(expected, results));
    }

    @Test
    public void isAliveTest2() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] world = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        int[][] results = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                results[i][j] = sim.isAlive(i,j,world);
            }
        }
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};

        System.out.println("");
        sim.printMatrix(results);
        System.out.println("");
        sim.printMatrix(expected);
        System.out.println("");

        assertTrue(java.util.Arrays.deepEquals(expected, results));
    }

    @Test
    public void isAliveTest3() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] world = {
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        int[][] results = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                results[i][j] = sim.isAlive(i,j,world);
            }
        }
        int[][] expected = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}};

        System.out.println("");
        sim.printMatrix(results);
        System.out.println("");
        sim.printMatrix(expected);
        System.out.println("");

        assertTrue(java.util.Arrays.deepEquals(expected, results));
    }

    @Test
    public void isAliveTest4() {
        ConwayGameOfLife sim = new ConwayGameOfLife();
        int[][] world = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}};

        int[][] results = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                results[i][j] = sim.isAlive(i,j,world);
            }
        }
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0}};

        System.out.println("");
        sim.printMatrix(results);
        System.out.println("");
        sim.printMatrix(expected);
        System.out.println("");

        assertTrue(java.util.Arrays.deepEquals(expected, results));
    }
}