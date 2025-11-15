package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.repository.ProductRepository;
import com.example.shopping_cart_api.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // TODO: This will be deleted
    @GetMapping("/greet")
    public Greeting greet(){
        String msg;
        msg = repository.fetchGreeting();
        msg = msg + " Dany!";
        return new Greeting(msg);
    }

    // TODO: This will be deleted
    public record Greeting(String greeting){}
}
