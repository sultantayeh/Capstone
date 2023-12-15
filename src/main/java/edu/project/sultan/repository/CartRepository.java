package edu.project.sultan.repository;

import edu.project.sultan.model.Burger;
import edu.project.sultan.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByCustomer_IdAndId(String CustomerId, Long cartId);
    Optional<Cart> findByCustomer_Id(String CustomerId);

}
