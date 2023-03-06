package com.market.timedeal.service;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import com.market.timedeal.dto.request.ProductRegisterDto;
import com.market.timedeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void registerProduct(ProductRegisterDto productRegisterDto) {
        Product newProduct = productRegisterDto.of();
        productRepository.saveAndFlush(newProduct);
    }

    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findProductByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public Page<Product> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

}