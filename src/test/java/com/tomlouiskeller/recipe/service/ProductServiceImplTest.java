package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Product;
import com.tomlouiskeller.recipe.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductServiceImplTest {

    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void getByNameReturnsUnitOfMeasurement() {
        String find = "Tomato";
        Product actual = productService.getByName(find);
        assertNotNull(actual);
        assertEquals(Product.class, actual.getClass());
    }

    // TODO: More findOrCreate tests
}