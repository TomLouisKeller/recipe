package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Product;
import com.tomlouiskeller.recipe.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // TODO: We have this findByNameOrCreate thing in 3 classes. Find a way to sort this out.
    public Product findByNameOrCreate(String name){
        Optional<Product> product = productRepository.findByName(name);
        if (product.isPresent())
            return product.get();
        else
            return create(name);
    }

    private Product create(String name) {
        Product product;
        product = new Product(name);
        productRepository.save(product);
        return product;
    }
}
