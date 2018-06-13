package com.tomlouiskeller.recipe.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Slf4j
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        String id = "4";
        category.setId(id);
        assertEquals(id, category.getId());

    }

    @Test
    public void toStringTest() {
        assertFalse(category.toString().contains("recipe"));
    }
}