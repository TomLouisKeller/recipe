package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Category;
import com.tomlouiskeller.recipe.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void getByNameReturnsUnitOfMeasurement() {
        String find = "Swiss";
        Category actual = categoryService.findOrCreate(find);
        assertNotNull(actual);
        assertEquals(Category.class, actual.getClass());
    }

    // TODO: More findOrCreate tests

    @Test
    public void findAllResultNotNull() {
        SortedSet<Category> categories = categoryService.findAll();
        assertNotNull(categories);
    }

    @Test
    public void findAllUsesRepository() {
        categoryService.findAll();
        verify(categoryRepository, times(1)).findAllByOrderByName();
    }

    @Test
    public void findAllReturnsWantedSortedSet() {
        SortedSet<Category> expected = new TreeSet<>();
        when(categoryRepository.findAllByOrderByName()).thenReturn(expected);
        SortedSet<Category> actual = categoryService.findAll();
        assertEquals(expected, actual);
    }
}