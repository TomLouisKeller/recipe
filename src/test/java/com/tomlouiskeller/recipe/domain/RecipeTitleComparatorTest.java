package com.tomlouiskeller.recipe.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class RecipeTitleComparatorTest {

    private Comparator<Recipe> comparator;

    @Before
    public void setUp() {
        comparator = Recipe.TitleComparator;
    }

    @Test
    public void titleComparatorR1R2Null() {
        Recipe r1 = null;
        Recipe r2 = null;

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }

    @Test
    public void titleComparatorR1Null() {
        Recipe r1 = null;
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void titleComparatorR2Null() {
        Recipe r1 = new Recipe();
        Recipe r2 = null;

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void titleComparatorBothTitleNull() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }

    @Test
    public void titleComparatorR1TitleNull() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r2.setTitle("Bee");

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void titleComparatorR2ITitleNull() {
        Recipe r1 = new Recipe();
        r1.setTitle("Aaa");
        Recipe r2 = new Recipe();

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void titleComparator1Before2() {
        Recipe r1 = new Recipe();
        r1.setTitle("Aaa");
        Recipe r2 = new Recipe();
        r2.setTitle("Bee");

        int actual = comparator.compare(r1, r2);

        assertTrue(actual < 0);
    }

    @Test
    public void titleComparator2Before1() {
        Recipe r1 = new Recipe();
        r1.setTitle("Cee");
        Recipe r2 = new Recipe();
        r2.setTitle("Bee");

        int actual = comparator.compare(r1, r2);

        assertTrue(actual > 0);
    }

    @Test
    public void titleComparator1And2Equal() {
        Recipe r1 = new Recipe();
        r1.setTitle("Bee");
        Recipe r2 = new Recipe();
        r2.setTitle("Bee");

        int actual = comparator.compare(r1, r2);

        assertTrue(actual == 0);
    }
}