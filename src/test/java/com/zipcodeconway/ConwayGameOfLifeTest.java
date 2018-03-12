package com.zipcodeconway;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import com.zipcodeconway.ConwayGameOfLife;

public class ConwayGameOfLifeTest {

    private ConwayGameOfLife sim;

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
    public void copyAndZeroOutTest() {
        // Given
        int[][] expectedNextArrayBefore = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0} };
        int[][] expectedNextArrayAfter = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0} };
        int[][] expectedCurrentArrayBefore = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0} };
        int[][] expectedCurrentArrayAfter = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0} };
        sim = new ConwayGameOfLife(5, expectedCurrentArrayBefore);
        String expectedNextArray = sim.rowsToString(expectedNextArrayAfter);
        String expectedCurrentArray = sim.rowsToString(expectedCurrentArrayAfter);
        // When
        sim.copyAndZeroOut(expectedNextArrayBefore, expectedCurrentArrayBefore);
        String actualNextArray = sim.rowsToString(sim.getNextArrayAfter());
        String actualCurrentArray = sim.rowsToString(sim.getCurrentArrayAfter());
        // Then
        Assert.assertEquals(expectedNextArray, actualNextArray);
        Assert.assertEquals(expectedCurrentArray, actualCurrentArray);

    }

}