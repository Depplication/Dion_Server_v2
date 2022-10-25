package com.dion.v2.domain.product.entity;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.owner.entity.Owner;
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
    @JoinColumn(name = "author_id")
    private Owner author;
    public void setAuthor(Owner author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "ad_id")
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
