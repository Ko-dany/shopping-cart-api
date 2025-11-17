package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.entity.CartItem;
import com.example.shopping_cart_api.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://shopping-cart-web.onrender.com")
public class CartController {
    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/cart")
    public List<CartItem> getAllCartItems() {
        return cartRepository.getAllCartItems();
    }

    @DeleteMapping("/emptycart")
    public String deleteAllCartItems(){
        try{
            cartRepository.deleteAllCartItems();
            return "All cart items deleted successfully";
        }catch(Exception e){
            return "Error occurred while deleting cart items";
        }
    }

    @DeleteMapping("/cart/{productId}")
    public String deleteProduct(@PathVariable int productId){
        if(cartRepository.getCartItemByCartItemId(productId) == null){ return "Cart item not found"; }

        try{
            cartRepository.deleteCartItemById(productId);
            return "Product added to cart successfully";
        }catch(Exception e){
            return "Error occurred while adding product to cart";
        }
    }

    @PostMapping("/cart/{productId}")
    public String addProductToCart(@PathVariable int productId){
        CartItem existingAddedProduct = cartRepository.getCartItemByProductId(productId);

        try{
            if(existingAddedProduct == null){
                cartRepository.addCartItem(productId);
            }else{
                cartRepository.updateQuantity(productId, existingAddedProduct.getQuantity() + 1);
            }
            return "Product added to cart successfully";
        }catch(Exception e){
            return "Error occurred while adding product to cart";
        }
    }
}
