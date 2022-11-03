package com.dion.v2.domain.product.entity;

import com.dion.v2.domain.advertising.entity.Advertising;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "advertising_id")
    private Advertising advertising;
    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

    private String productName;
    private String content;
    private Long price;

    @Builder
    public Product(String productName, String content, Long price) {
        this.productName = productName;
        this.content = content;
        this.price = price;
    }
}
