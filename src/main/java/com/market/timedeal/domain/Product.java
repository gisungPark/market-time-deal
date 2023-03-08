package com.market.timedeal.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    @ColumnDefault("0")
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    @ColumnDefault("0")
    private int quantity;

    @Override
    public String toString() {
        return id +" : " + name +" ";
    }

    public void purchase(int quantity) {
        this.quantity -= quantity;
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
