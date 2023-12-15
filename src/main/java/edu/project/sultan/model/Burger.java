package edu.project.sultan.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Burger {
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_sequence_generator")
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", initialValue = 0, allocationSize = 1)
    @Id
    private long id;

    private String imageUrl;

    private String title;
    private Double price;

    @ElementCollection
   private List<String> ingredients = new ArrayList<>();


    public Burger(String s, String burgerJava, double v, ArrayList<String> ingredients0) {
    this.imageUrl = s;
    this.title = burgerJava;
    this.price = v;
    this.ingredients = ingredients0;
    }
}
