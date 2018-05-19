package com.tomlouiskeller.recipe.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    private String ip; //InetAddress

    // Relations
    @ManyToOne(fetch = FetchType.EAGER)
    private Recipe recipe;

}
