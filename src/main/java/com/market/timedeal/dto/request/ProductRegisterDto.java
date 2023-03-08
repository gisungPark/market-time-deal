package com.market.timedeal.dto.request;

import com.market.timedeal.domain.Category;
import com.market.timedeal.domain.Product;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@Builder
@ToString
@AllArgsConstructor
public class ProductRegisterDto {
    @Enumerated(EnumType.STRING)
    private Category category;
    private String name;
    private int price;
    private int quantity;

    public Product of() {
        return Product.builder()
                .category(category != null ? category : Category.ETC)
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
