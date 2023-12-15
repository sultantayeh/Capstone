package edu.project.sultan.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Id
    private long id;
    @NonNull
    private String ingredient;


}
