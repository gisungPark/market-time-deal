package com.market.timedeal.service;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import com.market.timedeal.dto.request.ProductRegisterDto;
import com.market.timedeal.exception.NotFoundException;
import com.market.timedeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void registerProduct(ProductRegisterDto productRegisterDto) {
        Product newProduct = productRegisterDto.of();
        productRepository.saveAndFlush(newProduct);
    }

    public Product findByProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다."));
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

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public synchronized boolean decreaseProductQuantity(Product product, int quantity) {
        Product findProduct = findByProductById(product.getId());

        if (product.getQuantity() < quantity) {
            return false;
        }

        findProduct.purchase(quantity);
        productRepository.saveAndFlush(findProduct);
        return true;
    }

    @Transactional
    public boolean decreaseProductQuantityWithPessimistic(Product product, int quantity) {
        Product findProduct = productRepository.findByIdWithPessimisticLock(product.getId());

        if (product.getQuantity() < quantity) {
            return false;
        }

        findProduct.purchase(quantity);
        productRepository.saveAndFlush(findProduct);
        return true;
    }


}
