package com.tomlouiskeller.recipe.service;

import com.tomlouiskeller.recipe.domain.Product;
import com.tomlouiskeller.recipe.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getByName(String name){
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
