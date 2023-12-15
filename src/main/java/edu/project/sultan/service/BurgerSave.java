package edu.project.sultan.service;

import edu.project.sultan.model.Burger;
import edu.project.sultan.model.Ingredient;
import edu.project.sultan.repository.BurgerRepository;
import edu.project.sultan.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BurgerSave {

    @Bean
    public static CommandLineRunner allArtworkSave(BurgerRepository burgerRepository) {
        ArrayList<String> ingredients0 = new ArrayList<>();
        ArrayList<String> ingredients1 = new ArrayList<>();
        ArrayList<String> ingredients2 = new ArrayList<>();

        ingredients0.add("CSS");
        ingredients0.add("3 files binary");
        ingredients0.add("Red black Trees");


        ingredients1.add("Quick Sort");
        ingredients1.add("Insertion Sort");
        ingredients1.add("Depth First Search");

        ingredients2.add("Algorithm and Data Structures");
        ingredients2.add("Binary");
        ingredients2.add("CSS");






        return args -> {


            Burger burger0 = new Burger("/image/burger/burger0.webp", "Brand New Tech Burger", 32.43,
                    ingredients0);
            Burger burger1 = new Burger("/image/burger/burger1.webp", "Kotlin Burger", 25.8,
                    ingredients1);
            Burger burger2 = new Burger("/image/burger/burger2.webp", "Burger Java", 87.32,
                    ingredients2);
            Burger burger3 = new Burger("/image/burger/burger3.webp", "Kubernetes Taste", 56.32,
                    ingredients0);
            Burger burger4 = new Burger("/image/burger/burger4.webp", "Docker", 21.43,
                    ingredients1);
            Burger burger5 = new Burger("/image/burger/burger5.webp", "Cobol ", 33.43,
                    ingredients2);
            Burger burger6 = new Burger("/image/burger/burger6.webp", "Fortran ", 82.47,
                    ingredients0);
            Burger burger7 = new Burger("/image/burger/burger7.webp", "Java Script", 54.78,
                    ingredients1);
            Burger burger8 = new Burger("/image/burger/burger8.webp", "Spring Boot", 90.4,
                    ingredients2);
            Burger burger9 = new Burger("/image/burger/burger9.webp", "Flask", 32.2,
                    ingredients0);
            Burger burger10 = new Burger("/image/burger/burger10.webp", "GO", 22.53,
                    ingredients1);
            Burger burger11 = new Burger("/image/burger/burger11.webp", "Ruby On Rails", 21.03,
                    ingredients2);

            burgerRepository.saveAll(
                    List.of(burger0,
                            burger1,
                            burger2,
                            burger3,
                            burger4,
                            burger5,
                            burger6,
                            burger7,
                            burger8,
                            burger9,
                            burger10, burger11
                    ));

        };
    }

}
