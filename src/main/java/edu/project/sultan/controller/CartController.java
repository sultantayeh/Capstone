package edu.project.sultan.controller;

import edu.project.sultan.dto.BurgerData;
import edu.project.sultan.model.Burger;
import edu.project.sultan.model.Cart;
import edu.project.sultan.model.Customer;
import edu.project.sultan.model.Ingredient;
import edu.project.sultan.repository.BurgerRepository;
import edu.project.sultan.repository.CartRepository;
import edu.project.sultan.repository.CustomerRepository;
import edu.project.sultan.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/cart/burger", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:8080/"})
public class CartController {
    @Autowired
    private BurgerRepository burgerRepository;
    @Autowired

    private CartRepository cartRepository;
    @Autowired

    private CustomerRepository customerRepository;



    @GetMapping("/{userID}/{id}")
    public ResponseEntity<Burger> userCartByUserIdAndId(@PathVariable("userID") String userID, @PathVariable long id) {
        Optional<Cart> items = cartRepository.findByCustomer_Id(userID);



        if (items.isPresent()) {
            List<Burger> burgers = items.get().getBurgers();
            if (burgers.stream().anyMatch(product -> product.getId() == id)) {
                return new ResponseEntity<>(burgerRepository.findById(id).get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }


    @GetMapping("/{userID}")
    public ResponseEntity<List<Burger>> userCartById(@PathVariable("userID") String userID) {
        Optional<Cart> item = cartRepository.findByCustomer_Id(userID);
        if (item.isPresent()) {
            List<Burger> burgerList = item.get().getBurgers();
            if (!burgerList.isEmpty()) {
                return new ResponseEntity<>(burgerList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }




    @PostMapping(value = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart saVeUserCart(@RequestBody BurgerData burgerData) {


        try {
            Optional<Burger> burgerOptional = burgerRepository.findById(burgerData.getBurgerId());
           Cart cart;
            Burger burger;
            if (burgerOptional.isPresent()) {

                burger = burgerOptional.get();
                Optional<Customer> customerOptional = customerRepository.findById(burgerData.getCustomerId());

                if (customerOptional.isPresent()) {
                    if(cartRepository.findByCustomer_Id(burgerData.getCustomerId()).isPresent()) {
                        cart = cartRepository.findByCustomer_Id(burgerData.getCustomerId()).get();
                    }
                    else{
                        cart = new Cart();
                    }
                    cart.getBurgers().add(burger);
                    Customer customer = customerOptional.get();
                    cart.setCustomer(customer);


                    return cartRepository.save(cart);
                }


                cart = new Cart();
                cart.getBurgers().add(burger);
                Customer customer = new Customer();
                customer.setId(burgerData.getCustomerId());
                cart.setCustomer(customer);
                customerRepository.save(customer);
                return cartRepository.save(cart);
            }
            else{
                throw new Exception("Burger not Found");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }


    }

    @Transactional
    @DeleteMapping("/{userId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@PathVariable("userId") String userId, @PathVariable("id") long id) {
        try {
            Optional<Cart> data = cartRepository.findByCustomer_Id(userId);
            if (data.isPresent()) {
                List<Burger> products = data.get().getBurgers();
                for (Iterator<Burger> iterator = products.iterator(); iterator.hasNext(); ) {
                    Burger helper = iterator.next();
                    if (burgerRepository.findById(id).get().getId() == helper.getId()) {
                        iterator.remove();
                    }
                }

            }

        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }


}
