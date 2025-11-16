package com.example.shopping_cart_api.repository;

import com.example.shopping_cart_api.entity.CartItem;
import com.example.shopping_cart_api.entity.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CartItem getProductById(int productId) {
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM cart_items WHERE product_id = ?",
                    (rs, rowNum) -> new CartItem(
                            rs.getInt("id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    ),
                    productId
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    public boolean addProduct(int id) {
        String sql = """
        INSERT INTO cart_items (product_id, quantity) 
        VALUES (?, 1)
        """;

        int rows = jdbcTemplate.update(
                sql,
                id
        );

        return rows > 0;
    }

    public boolean updateQuantity(int product_id, int quantity) {
        String sql = """
        UPDATE cart_items
        SET quantity = ?
        WHERE product_id = ?
        """;

        int rows = jdbcTemplate.update(
                sql,
                quantity,
                product_id
        );

        return rows > 0;
    }
}
