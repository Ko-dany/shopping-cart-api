package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.entity.CartItem;
import com.example.shopping_cart_api.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @PostMapping("/{productId}")
    public String addProductToCart(@PathVariable int productId){
        CartItem existingAddedProduct = cartRepository.getProductById(productId);

        try{
            if(existingAddedProduct == null){
                cartRepository.addProduct(productId);
            }else{
                cartRepository.updateQuantity(productId, existingAddedProduct.getQuantity() + 1);
            }
            return "Product added to cart successfully";
        }catch(Exception e){
            return "Error occurred while adding product to cart";
        }
    }
}
