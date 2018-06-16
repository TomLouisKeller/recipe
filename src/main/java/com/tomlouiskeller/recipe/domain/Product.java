package com.tomlouiskeller.recipe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document
public class Product {

    @Id
    private String id;
    private String name;

    public Product(String name) {
        this.name = name;
    }
}
