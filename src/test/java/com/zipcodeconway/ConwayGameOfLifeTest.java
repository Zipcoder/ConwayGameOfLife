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

//    @Test
//    public void copyAndZeroOutTest(){
//        ConwayGameOfLife sim = new ConwayGameOfLife(5);
//        //Given
//
//    }

    @Test
    public void isAliveTest(){

        ConwayGameOfLife sim = new ConwayGameOfLife(5);
        //Given

        Integer row = 2;
        Integer col= 2;
        int[][] world = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};


        Integer expected = 1;
        Integer actual = sim.isAlive(row, col, world);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void edgeTest(){

        ConwayGameOfLife simEdge = new ConwayGameOfLife(5);
        //Given

        Integer row =4;
        Integer col = 4;

        int[][]world = {
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0}};


        Integer expected = 1;
        Integer actual = simEdge.isAlive(row, col, world);

        Assert.assertEquals(expected,actual);
    }


}