package edu.project.sultan.controller;

import edu.project.sultan.model.Burger;
import edu.project.sultan.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private BurgerRepository burgerRepository;

    @GetMapping

    public String getMenuPage(Model model){
    Iterable<Burger> burgerList = burgerRepository.findAll();
    System.out.println(burgerList);
    model.addAttribute("burgers",burgerList);
        return "menu";
    }
}
