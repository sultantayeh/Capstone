package edu.project.sultan.repository;

import edu.project.sultan.model.Burger;
import edu.project.sultan.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findByEmail(String s);
}
