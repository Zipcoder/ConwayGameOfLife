package com.zipcodeconway;

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
    public void testIsAlive(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        int expected = 1;
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(5, start);
        int result = conwayGameOfLife.isAlive(1, 2, start);
        assertEquals(expected, result);
    }

    @Test
    public void testCheckForNextGen(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        int expected = 1;
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(5, start);
        int result = conwayGameOfLife.checkForNextGen(3, 0);
        assertEquals(expected, result);
    }
    @Test
    public void testGetNeighborsArray(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};

        Integer[] expected = {0, 1, 0, 1, 0, 0, 1, 0};
        ConwayGameOfLife conwayGameOfLife = new ConwayGameOfLife(5, start);
        Integer[] result = conwayGameOfLife.getNeighborsArray(1, 2, start);
        String expectedString = Arrays.toString(expected);
        String actualString = Arrays.toString(result);
        assertEquals(expectedString, actualString);
    }
}