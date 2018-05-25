package com.tomlouiskeller.recipe.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude="recipe")
@ToString(exclude = "recipe")
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    private String ip; // TODO: InetAddress

    // Relations
    @ManyToOne(fetch = FetchType.EAGER)
    private Recipe recipe;

}
