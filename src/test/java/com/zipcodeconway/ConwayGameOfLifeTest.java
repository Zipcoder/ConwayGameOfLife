package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConwayGameOfLifeTest {

    ConwayGameOfLife game;

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
        int[][] results = sim.simulate(10);
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
        int[][] results = sim.simulate(11);
        System.out.println(results[2][2]);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest3() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(10);
        System.out.println(results[2][2]);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest4() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(5);
        System.out.println(results[2][2]);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest5() {
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        ConwayGameOfLife sim = new ConwayGameOfLife(5, start);
        int[][] results = sim.simulate(1);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void runTest6() {
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
        int[][] results = sim.simulate(2);
        assertTrue(java.util.Arrays.deepEquals(results, expected));
    }

    @Test
    public void randomStartTest(){
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
    }

    @Test
    public void isAlive2NeighborTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 1;
        int actual = game.isAlive(2, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAlive0NeighborTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 0;
        int actual = game.isAlive(2, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAlive3NeighborTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 1;
        int actual = game.isAlive(2, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAlive4NeighborTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 0;
        int actual = game.isAlive(2, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveReproductionTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 1;
        int actual = game.isAlive(2, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveEdgeTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 0;
        int actual = game.isAlive(4, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isAliveEdgeTest2(){
        int[][] start = {
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        int expected = 1;
        int actual = game.isAlive(4, 2, start);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loopCheck(){
        game = new ConwayGameOfLife(5);
        Assert.assertEquals(game.loopCheck(3), 3);
    }

    @Test
    public void loopCheckEdge(){
        game = new ConwayGameOfLife(5);
        Assert.assertEquals(4, game.loopCheck(-1));
    }

    @Test
    public void loopCheckEdge2(){
        game = new ConwayGameOfLife(5);
        Assert.assertEquals(0, game.loopCheck(5));
    }

    @Test
    public void loopCheckEdge3(){
        game = new ConwayGameOfLife(5);
        Assert.assertEquals(3, game.loopCheck(-2));
    }

    @Test
    public void loopCheckEdge4(){
        game = new ConwayGameOfLife(5);
        Assert.assertEquals(1, game.loopCheck(6));
    }

    @Test
    public void copyZeroOutTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        game.copyAndZeroOut(expected);
        Assert.assertEquals(game.getBoard(), expected);
    }

    @Test
    public void constructorTest(){
        int[][] start = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}};
        game = new ConwayGameOfLife(5, start);
        Assert.assertEquals(game.getBoard(), start);
    }

}