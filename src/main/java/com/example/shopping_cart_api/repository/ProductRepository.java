package com.example.shopping_cart_api.repository;

import com.example.shopping_cart_api.entity.Product;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Product getProductById(int id) {
        try{
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM products WHERE id = ?",
                    (rs, rowNum) -> new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("sku"),
                            rs.getBoolean("is_active"),
                            rs.getString("image")
                    ),
                    id
            );
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public boolean addProduct(Product product) {
        String sql = """
        INSERT INTO products (name, description, price, sku, is_active, image)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        int rows = jdbcTemplate.update(
                sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.isActive(),
                product.getImage()
        );

        return rows > 0;
    }

    public boolean updateProduct(Product product) {
        String sql = """
        UPDATE products
        SET name = ?, description = ?, price = ?, sku = ?, is_active = ?, image = ?
        WHERE id = ?
        """;

        int rows = jdbcTemplate.update(
                sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.isActive(),
                product.getImage(),
                product.getId()
        );

        return rows > 0;
    }

    public boolean deleteProductById(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        int rows = jdbcTemplate.update(sql, id);

        return rows > 0;
    }
}
