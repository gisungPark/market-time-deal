package com.market.timedeal.service;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import com.market.timedeal.dto.request.ProductDto;
import com.market.timedeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void registerProduct(ProductDto productDto) {
        Product newProduct = productDto.of();
        productRepository.saveAndFlush(newProduct);
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
