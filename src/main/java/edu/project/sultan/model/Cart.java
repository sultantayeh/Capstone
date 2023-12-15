package edu.project.sultan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @OneToMany
    private List<Burger> burgers = new ArrayList<>();
    @OneToOne
    private Customer customer;

}
