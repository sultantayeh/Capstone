package edu.project.sultan.repository;

import edu.project.sultan.model.Burger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BurgerRepository extends CrudRepository<Burger, Long> {


}
