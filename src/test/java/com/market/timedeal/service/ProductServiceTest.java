package com.market.timedeal.service;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import com.market.timedeal.dto.request.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

//    @BeforeEach
//    @Transactional
//    public void beforeEach(){
//        productService.registerProduct(new ProductDto(Category.FOOD, "사과", 9000, 200));
//        productService.registerProduct(new ProductDto(Category.FOOD, "바나나", 12000, 90));
//        productService.registerProduct(new ProductDto(Category.FOOD, "파인애플", 22000, 300));
//        productService.registerProduct(new ProductDto(Category.BOOK, "책", 15000, 45));
//
//
//    }

    @Test
    public void 상품등록_테스트() {
        ProductDto newProduct = ProductDto.builder()
                .name("카카오")
                .category(Category.FOOD)
                .price(7000)
                .quantity(1200).build();
        productService.registerProduct(newProduct);
    }


    @Test
    public void 상품_카테고리조회_테스트() {
        List<Product> products = productService.findProductByCategory(Category.FOOD);
        for(Product p : products){
            System.out.println(p.toString());
        }
    }

    @Test
    public void 상품_이름조회_테스트(){
        Product findProduct = productService.findProductByName("카카오");
        System.out.println(findProduct.toString());
    }





}