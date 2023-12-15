package edu.project.sultan.controller;

import edu.project.sultan.model.Customer;
import edu.project.sultan.repository.CustomerRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/api")


public class CustomerController {
    private String confirmation;
    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping("/signup")
    public String handleFormSubmission(RedirectAttributes redirectAttributes,@RequestParam String uname_signup,@RequestParam String pswd_signup, @RequestParam String confirm_pswd) {
        // Process the submitted data

        Optional<Customer> customerOptional = customerRepository.findByEmail(uname_signup.trim());
        if(!pswd_signup.equals(confirm_pswd)){
            redirectAttributes.addFlashAttribute("confirmation", "Sorry, could not register your account the passwords you entered do not match. Please try again");
            return "redirect:/";
        }
        else if(customerOptional.isEmpty()) {
            Customer customer = new Customer();
            customer.setEmail(uname_signup.trim());
            customer.setPassword(pswd_signup.trim());
            customerRepository.save(customer);
            redirectAttributes.addFlashAttribute("confirmation", "Congratulations you have successfully sign up to our system, please login to see your best burger deal");
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("confirmation", "Sorry this username is already existed in our system, please try again");

        // Return a response
        return "redirect:/";
    }  
    @PostMapping("/login")

    public String processForm(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response, @RequestParam String uname, @RequestParam String pswd) {
        // Process the form data
        Optional<Customer> customerOptional = customerRepository.findByEmail(uname.trim());
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if(customer.getPassword().trim().equals(pswd.trim())){
                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    // Identify the cookie you want to override
                    for (Cookie cookie : cookies) {
                        if ("user12345".equals(cookie.getName())) {
                            // Modify the value of the existing cookie
                            cookie.setValue(customer.getId());

                            // Set additional cookie properties if needed (e.g., domain, path, maxAge)
                            // cookie.setDomain("example.com");
                            cookie.setPath("/");
                            cookie.setMaxAge(3600); // 1 hour in seconds

                            // Add the modified cookie to the response
                            response.addCookie(cookie);

                            System.out.println("Cookie updated. New value: " + cookie.getValue());
                            break;  // Stop iterating once the cookie is found and updated
                        }
                    }
                } else {
                    System.out.println("No cookies found in the request.");
                }

                return "redirect:/menu";
            }
        }// Redirect to another page
       redirectAttributes.addFlashAttribute("confirmation", "Email or password is incorrect please try again");
        return "redirect:/";
    }


    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }



}
 