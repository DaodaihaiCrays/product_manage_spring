package com.example.store_spring.repository;

import com.example.store_spring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findByName(String name);
}
