package com.market.timedeal.controller;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import com.market.timedeal.dto.CustomResponseEntity;
import com.market.timedeal.dto.request.ProductRegisterDto;
import com.market.timedeal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public void register(ProductRegisterDto productRegisterDto) {
        productService.registerProduct(productRegisterDto);
    }

    @GetMapping()
    public ResponseEntity list(@RequestParam("page") int page, @RequestParam("size") int size) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(new CustomResponseEntity<Page<Product>>("",
                                    productService.findAll(PageRequest.of(page, size))));
    }

    public void findBuyer() {

    }

    @PostConstruct
    public void postConstruct() {
        for (int i = 0; i < 100; i++) {
            ProductRegisterDto newProduct = ProductRegisterDto.builder()
                    .category(Category.FOOD)
                    .name("product0" + i)
                    .price(i * 100 + 1300 + (i * 70))
                    .quantity(i * 20)
                    .build();

            productService.registerProduct(newProduct);
        }
    }

}
