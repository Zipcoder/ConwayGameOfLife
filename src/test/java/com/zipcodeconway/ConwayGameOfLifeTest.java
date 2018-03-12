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
    public void isAliveTest(){
        ConwayGameOfLife sim = new ConwayGameOfLife(5);

        //Given
        int row = 2;
        int col = 2;
        int [][] world = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        //When
        // 1 means alive
        int expected = 1;
        int actual = sim.isAlive(row, col, world);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveEdgeTest(){
        ConwayGameOfLife sim = new ConwayGameOfLife(5);

        //Given
        int row = 0;
        int col = 2;
        int [][] world = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0}};
        //When
        // 1 means alive
        int expected = 1;
        int actual = sim.isAlive(row, col, world);

        //Then
        Assert.assertEquals(expected, actual);
    }
}