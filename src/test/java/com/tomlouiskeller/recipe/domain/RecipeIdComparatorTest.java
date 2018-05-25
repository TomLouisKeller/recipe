package com.tomlouiskeller.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class RecipeIdComparatorTest {

    private Comparator<Recipe> comparator;

    @Before
    public void setUp() {
        comparator = Recipe.IdComparator;
    }

    @Test
    public void idComparatorR1R2Null() {
        Recipe r1 = null;
        Recipe r2 = null;

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }

    @Test
    public void idComparatorR1Null() {
        Recipe r1 = null;
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void idComparatorR2Null() {
        Recipe r1 = new Recipe();
        Recipe r2 = null;

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void idComparatorBothIdNull() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }

    @Test
    public void idComparatorR1IdNull() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r2.setId(2L);

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void idComparatorR2IdNull() {
        Recipe r1 = new Recipe();
        r1.setId(1L);
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void idComparator1Before2() {
        Recipe r1 = new Recipe();
        r1.setId(1L);
        Recipe r2 = new Recipe();
        r2.setId(2L);

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void idComparator2Before1() {
        Recipe r1 = new Recipe();
        r1.setId(3L);
        Recipe r2 = new Recipe();
        r2.setId(2L);

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void idComparator1And2Equal() {
        Recipe r1 = new Recipe();
        r1.setId(2L);
        Recipe r2 = new Recipe();
        r2.setId(2L);

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }
}