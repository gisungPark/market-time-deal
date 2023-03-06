package com.market.timedeal.repository;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    public List<Product> findByCategory(@Param("category") Category category);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findByName(@Param("name") String name);

}