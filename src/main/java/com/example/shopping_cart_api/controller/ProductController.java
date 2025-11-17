package com.example.shopping_cart_api.controller;

import com.example.shopping_cart_api.entity.Product;
import com.example.shopping_cart_api.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "https://shopping-cart-web.onrender.com")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public List<Product> GetAllProducts(){
        return productRepository.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product GetProductById(@PathVariable int id){
        return productRepository.getProductById(id);
    }

    @PostMapping("")
    public String AddProduct(@RequestBody Product product){
        try{
            productRepository.addProduct(product);
            return "Product added successfully";
        }catch(Exception e){
            return "Error occurred while adding Product";
        }
    }

    @PutMapping("/{id}")
    public String UpdateProduct(@PathVariable int id, @RequestBody Product product){
        if(productRepository.getProductById(id) == null){ return "Product not found"; }
        if(id != product.getId()) { return "Id mismatch"; }
        try{
            productRepository.updateProduct(product);
            return "Product updated successfully";
        }catch(Exception e){
            return "Error occurred while updating Product";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        if(productRepository.getProductById(id) == null){ return "Product not found"; }
        try{
            productRepository.deleteProductById(id);
            return "Product deleted successfully";
        }catch(Exception e){
            return "Error occurred while deleting Product";
        }
    }
}
