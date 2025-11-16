package com.example.shopping_cart_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CartItem {
    @Id
    int id;
    int productId;
    int quantity;

    public CartItem(int id, int productId,  int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
