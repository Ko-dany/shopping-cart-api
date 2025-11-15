package com.example.shopping_cart_api.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO: This is to check DB connection. Should be deleted.
    public String fetchGreeting(){
        return jdbcTemplate.queryForObject("SELECT message FROM greetings ORDER BY id DESC LIMIT 1", String.class);
    }
}
