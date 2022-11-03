package com.dion.v2.domain.advertising.entity;

import com.dion.v2.domain.advertising.type.Category;
import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.product.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Owner author;
    public void setOwner(Owner author) {
        this.author = author;
    }

    @Column(nullable = false)
    private String adTitle;

    @Lob
    @Column(nullable = false)
    private String adContent;

    @Lob
    @Column(nullable = false)
    private String storeExplain;

    private String email;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "advertising", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productList;
    public void addProduct(Product product) {
        product.setAdvertising(this);
        getProductList().add(product);
    }

    @Builder
    public Advertising(
            String adTitle, String adContent,
            String storeExplain, String email,
            LocalDateTime startDate, LocalDateTime endDate,
            Category category, List<Product> productList) {
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.storeExplain = storeExplain;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.productList = productList;
    }
}
