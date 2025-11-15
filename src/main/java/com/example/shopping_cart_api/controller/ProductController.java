package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.entity.Product;
import com.example.shopping_cart_api.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    public List<Product> GetAllProducts(){
        return repository.getAllProducts();
    }
}
