package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

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
    public void isAliveTest1() {
        ConwayGameOfLife sim = new ConwayGameOfLife(5);
        int row = 2;
        int col = 2;
        int[][] world = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int expected = 1;
        int actual = sim.isAlive(row, col, world);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveTest2() {
        ConwayGameOfLife sim = new ConwayGameOfLife(5);
        int row = 1;
        int col = 4;
        int[][] world = {
                {1, 0, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 1},
                {1, 0, 0, 1, 1}};
        int expected = 0;
        int actual = sim.isAlive(row, col, world);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveTest3() {
        ConwayGameOfLife sim = new ConwayGameOfLife(5);
        int row = 4;
        int col = 2;
        int[][] world = {
                {1, 0, 0, 1, 0},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 1, 1}};
        int expected = 1;
        int actual = sim.isAlive(row, col, world);
        Assert.assertEquals(expected, actual);
    }

}