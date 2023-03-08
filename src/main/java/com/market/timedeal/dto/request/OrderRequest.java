package com.market.timedeal.dto.request;

import com.market.timedeal.domain.Order;
import com.market.timedeal.domain.Product;
import com.market.timedeal.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class OrderRequest {
    private Long id;
    private Product product;
    private int quantity;

    public Order createOrderEntity(User customer, int price) {
        return Order.builder()
                .userId(customer.getId())
                .product(this.product)
                .quantity(this.quantity)
                .price(price)
                .orderDtm(LocalDateTime.now())
                .build();
    }
}
