package com.example.shopping_cart_api.repository;

import com.example.shopping_cart_api.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts(){
        return jdbcTemplate.query(
                "SELECT * FROM products",
                /* Convert data row retrieved from PostgreSQL into Product entity */
                (rs, rowNum) -> new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("sku"),
                        rs.getBoolean("is_active"),
                        rs.getString("image")
                )
        );
    }
}
